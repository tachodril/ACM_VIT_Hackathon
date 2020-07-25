package nitjsr.team.in.ragnarok.Fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import nitjsr.team.in.ragnarok.R;
import nitjsr.team.in.ragnarok.utils.AppConstants;
import nitjsr.team.in.ragnarok.utils.MyFileContentProvider;

import static android.app.Activity.RESULT_OK;

public class ScanListFragment extends Fragment {

    public Button detectTextBtn;
    public ImageView imageView,captureImageBtn;
    public TextView textView;
    public final int PICK_IMAGE_REQUEST = 71;
    public Bitmap imageBitmap;
    public Uri filePath;
    public boolean ifImageAdded=false;
    public final int CAMERA_RESULT = 1;
    public static final int PERMISSION_REQUEST_CODE = 200;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_scanlist, container, false);

        captureImageBtn = root.findViewById(R.id.capture_image);
        detectTextBtn = (Button) root.findViewById(R.id.detect_text_image);
        imageView = (ImageView) root.findViewById(R.id.image_view);
        textView = (TextView) root.findViewById(R.id.text_display);

        captureImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
                textView.setText("");
            }
        });

        detectTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ifImageAdded){
                    Toast.makeText(getContext(), "Add an Image", Toast.LENGTH_SHORT).show();
                    return;
                }
                detectTextFromImage();
            }
        });

        return root;
    }

    private void selectImage() {
        final CharSequence[] options = {"Take a Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Upload your Shopping List");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take a Photo")) {
                    //Camera Button
                    if (checkCameraPermission()) {
                        //main logic or main code
                        openCamera();
                    } else {
                        requestPermission();
                    }

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private boolean checkCameraPermission() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    openCamera();
//                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("Camera permission required to scan shopping list!",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
            }
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getContext())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    public void openCamera() {
        PackageManager pm = getContext().getPackageManager();

        if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            i.putExtra(MediaStore.EXTRA_OUTPUT, MyFileContentProvider.CONTENT_URI);
            startActivityForResult(i, CAMERA_RESULT);
        } else {
            Toast.makeText(getContext(), "Camera is not available", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == CAMERA_RESULT) {
            File out = new File(getContext().getFilesDir(), "newImage.jpg");
            if(!out.exists()) {
                Toast.makeText(getContext(),"Error while capturing image!", Toast.LENGTH_LONG).show();
                return;
            }
            Bitmap mBitmap = BitmapFactory.decodeFile(out.getAbsolutePath());
            //rotating bitMap by 90 degrees
//            float degrees = 90;
//            Matrix matrix = new Matrix();
//            matrix.setRotate(degrees);
//            Bitmap output=mBitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
            imageView.setImageBitmap(mBitmap);
            imageBitmap=mBitmap;
            ifImageAdded=true;

        } else if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
                imageView.setImageBitmap(imageBitmap);
                ifImageAdded=true;
            } catch (Exception e) {

            }
        }


        if(ifImageAdded){
            detectTextBtn.setEnabled(true);
            detectTextBtn.setAlpha(0.9f);
        }
    }

    private void detectTextFromImage() {

        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextRecognizer firebaseVisionTextRecognizer = FirebaseVision.getInstance().getOnDeviceTextRecognizer();

        firebaseVisionTextRecognizer.processImage(firebaseVisionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                displayTextFromImage(firebaseVisionText);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error: ", e.getMessage());
            }
        });
    }

    private void displayTextFromImage(FirebaseVisionText firebaseVisionText) {

        List<FirebaseVisionText.TextBlock> blockList = firebaseVisionText.getTextBlocks();
        if (blockList.size() == 0) {
            Toast.makeText(getContext(), "No Text Found!", Toast.LENGTH_SHORT).show();
        } else {
//            for(FirebaseVisionText.TextBlock block: firebaseVisionText.getTextBlocks() ){
//
//                String text=block.getText();
//                textView.setText(text);
//            }

            String s = "";
            for (int i = 0; i < blockList.size(); i++) {
                List<FirebaseVisionText.Line> lines = blockList.get(i).getLines();
                for (int j = 0; j < lines.size(); j++) {
                    List<FirebaseVisionText.Element> elements = lines.get(j).getElements();
                    s += "\n";
                    for (int k = 0; k < elements.size(); k++) {
                        s += elements.get(k).getText() + " ";
                    }
                }
            }
            textView.setText(s);
            Log.e("displayTextFromImage: ",s);
            AppConstants.listFromScan = s;
            Toast.makeText(getContext(), ""+s, Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(this, CreateShoppingListActivity.class));
//            finish();
        }
    }

}