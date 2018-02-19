package space.personal.youssefalmkari.inappbrowser;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView webView;
    // @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.backdrop)
    ImageView backdrop;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;

    private String webURL = "https://reactjs.org/";
    private String headerImageUrl =
            "https://cdn-images-1.medium.com/max/2000/1*kt9otqHk14BZIMNruiG0BA.png";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind
        ButterKnife.bind(this);

        // initialize toolbar
        initializeToolbar();

        // WebView
        webView.getSettings().setJavaScriptEnabled( true );
        webView.loadUrl( webURL );
        webView.setHorizontalScrollBarEnabled( false );

    }

    /**
     * Show and hide toolbar when scrolling
     */
    public void initializeToolbar()
    {
        collapsingToolbarLayout.setTitle( " " );
        appBarLayout.setExpanded( true );

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener()
        {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
            {
                if ( scrollRange == -1 )
                    scrollRange = appBarLayout.getTotalScrollRange();
                if ( scrollRange + verticalOffset == 0)
                {
                    collapsingToolbarLayout.setTitle(" Web View");
                    isShow = true;
                }
                else if ( isShow )
                {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });

        // loading toolbar header image
        Glide.with(getApplicationContext()).load(headerImageUrl)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(backdrop);

    }


}