package com.test.ecommerce.Model;

import com.test.ecommerce.R;

import java.util.ArrayList;

public class Slide {
    private static final ArrayList<Integer> slides = new ArrayList<Integer>() {{

        add(R.drawable.picture);

        add(R.drawable.picture);

        add(R.drawable.picture);

       /* add(R.drawable.slide1);

        add(R.drawable.slide5);

        add(R.drawable.slide6);

        add(R.drawable.slide7);*/

    }};



    public static ArrayList<Integer> getSlides() {

        return slides;

    }
}
