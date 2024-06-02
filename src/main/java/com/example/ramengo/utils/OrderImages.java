package com.example.ramengo.utils;

import com.example.ramengo.enums.BrothEnum;
import com.example.ramengo.enums.ImagesOrderEnum;
import com.example.ramengo.enums.ProteinEnum;

import java.util.HashMap;
import java.util.Map;

public class OrderImages {

    private static final Map<String, String> imageMap = new HashMap<>();

    static {
        imageMap.put(BrothEnum.SHOYU.toString() + "_" + ProteinEnum.KARAAGUE.toString(), ImagesOrderEnum.SHOYU.toString());
        imageMap.put(BrothEnum.MISO.toString() + "_" + ProteinEnum.CHASU.toString(), ImagesOrderEnum.MISO.toString());
    }

    public static String getOrderImage(BrothEnum broth, ProteinEnum protein) {
        return imageMap.getOrDefault(broth.toString() + "_" + protein.toString(), ImagesOrderEnum.SALT.toString());
    }
}
