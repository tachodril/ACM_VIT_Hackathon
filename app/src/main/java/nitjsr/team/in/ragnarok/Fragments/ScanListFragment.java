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

import static android.app.Activity.RESULT_OK;

public class ScanListFragment extends Fragment {

    public Button detectTextBtn;
    public ImageView imageView,captureImageBtn;
    public TextView textView;
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public final int PICK_IMAGE_REQUEST = 71;
    public Bitmap imageBitmap;
    public Uri filePath;
    public boolean ifImageAdded=false;
    public final int CAMERA_RESULT = 1;
    public static final int PERMISSION_REQUEST_CODE = 200;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_scanlist, container, false);

        return root;
    }

}