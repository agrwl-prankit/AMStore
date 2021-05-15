package com.prankit.amstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;

public class GeneratePdfActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_pdf);

        createPdf();

    }


    private void createPdf() {
        PdfGenerator.getBuilder()
                .setContext(this)
                .fromLayoutXMLSource()
                .fromLayoutXML(R.layout.activity_generate_pdf)
                .setPageSize(PdfGenerator.PageSize.A4)
                .setFileName("Test-Pdf")
                .setFolderName(Environment.DIRECTORY_DOWNLOADS)
                .openPDFafterGeneration(true)
                .build(new PdfGeneratorListener() {
                    @Override
                    public void onStartPDFGeneration() {
                        Log.i("Pdfstart", "start");
                        Toast.makeText(GeneratePdfActivity.this, "start", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFinishPDFGeneration() {
                        Log.i("Pdffinish", "finish");
                        Toast.makeText(GeneratePdfActivity.this, "finish", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(FailureResponse failureResponse) {
                        super.onFailure(failureResponse);
                        Log.i("Pdffailure", failureResponse.getErrorMessage());
                    }

                    @Override
                    public void onSuccess(SuccessResponse response) {
                        super.onSuccess(response);
                        Log.i("Pdffinish", "success " + response.getPath() + " " + response.getFile());
                        Toast.makeText(GeneratePdfActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}