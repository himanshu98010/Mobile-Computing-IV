package co.martinbaciga.drawingtest;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import co.martinbaciga.drawingtest.domain.manager.FileManager;
import co.martinbaciga.drawingtest.ui.component.DrawingView;
import co.martinbaciga.drawingtest.ui.dialog.StrokeSelectorDialog;

public class MainActivity extends AppCompatActivity {

    private DrawingView mDrawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawingView = findViewById(R.id.draw_view);

        Button btnStroke = findViewById(R.id.btn_stroke);
        Button btnColor = findViewById(R.id.btn_color);
        Button btnUndo = findViewById(R.id.btn_undo);
        Button btnClear = findViewById(R.id.btn_clear);
        Button btnSave = findViewById(R.id.btn_save);

        btnStroke.setOnClickListener(v -> showStrokeDialog());
        btnColor.setOnClickListener(v -> toggleColor()); // Simple toggle for demo
        btnUndo.setOnClickListener(v -> mDrawingView.undo());
        btnClear.setOnClickListener(v -> mDrawingView.clearCanvas());
        btnSave.setOnClickListener(v -> saveImage());
    }

    private void showStrokeDialog() {
        StrokeSelectorDialog dialog = StrokeSelectorDialog.newInstance(10, 50);
        dialog.setOnStrokeSelectedListener(stroke -> mDrawingView.setPaintStrokeWidth(stroke));
        dialog.show(getSupportFragmentManager(), "StrokeDialog");
    }

    private void toggleColor() {
        // Simple cycler between Red, Blue, and Black for simplicity
        int color = (int) (Math.random() * 0xFFFFFF) + 0xFF000000;
        mDrawingView.setPaintColor(color);
        Toast.makeText(this, "Color Changed!", Toast.LENGTH_SHORT).show();
    }

    private void saveImage() {
        Uri uri = FileManager.saveBitmap(this, mDrawingView.getBitmap());
        if (uri != null) {
            Toast.makeText(this, "Saved: " + uri.getPath(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Error saving image", Toast.LENGTH_SHORT).show();
        }
    }
}