package com.example.cobe.articlesapp.common.constants;

import java.util.Arrays;

/**
 * Created by cobe on 30/03/2018.
 */

public enum ArticleType {
    POLITIC, SPORT, DEVELOP, OTHER;

    public static int getTypeIndex(ArticleType type) {
        return Arrays.asList(ArticleType.values()).indexOf(type);
    }
}
