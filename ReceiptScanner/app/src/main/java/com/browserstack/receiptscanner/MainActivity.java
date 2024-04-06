package com.browserstack.receiptscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.microblink.BlinkReceiptSdk;
import com.microblink.CameraScanActivity;
import com.microblink.FrameCharacteristics;
import com.microblink.Media;
import com.microblink.ScanOptions;
import com.microblink.core.Retailer;
import com.microblink.core.ScanResults;

public class MainActivity extends AppCompatActivity {
    private static final int SCAN_RECEIPT_REQUEST = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MicroBli
//        BlinkReceiptSdk.i
//        BlinkReceiptSdk.initialize( getApplicationContext() );

    }

    public void startScan(View view) {
        ScanOptions scanOptions = ScanOptions.newBuilder()
                .retailer( Retailer.UNKNOWN )
                .frameCharacteristics( FrameCharacteristics.newBuilder()
                        .storeFrames( true )
                        .compressionQuality( 100 )
                        .externalStorage( false ) .build() )
                .logoDetection( true )
                .build();

        Bundle bundle = new Bundle();

        bundle.putParcelable( CameraScanActivity.SCAN_OPTIONS_EXTRA, scanOptions );

        Intent intent = new Intent( this, CameraScanActivity.class )
                .putExtra( CameraScanActivity.BUNDLE_EXTRA, bundle );

        startActivityForResult( intent, SCAN_RECEIPT_REQUEST );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode == SCAN_RECEIPT_REQUEST && resultCode == Activity.RESULT_OK ) {
            ScanResults brScanResults = data.getParcelableExtra( CameraScanActivity.DATA_EXTRA );

            Media media = data.getParcelableExtra( CameraScanActivity.MEDIA_EXTRA );
        }
    }

}