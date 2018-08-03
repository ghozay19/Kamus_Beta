package com.ghozay19.kamus.Database;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_ENGLISH_BAHASA = "eng_bhs";
    static String TABLE_BAHASA_ENGLISH = "bhs_eng";

    static final class EnglishBahasaColumns implements BaseColumns{

        //word
        static String WORD_ENGLISH = "worEng";
        //detail
        static String DETAIL_ENGLISH = "detEng";
    }

    static final class BahasaEnglishColumns implements BaseColumns{

        //word
        static String WORD_BAHASA = "worBhs";
        //detail
        static String DETAIL_BAHASA = "detBhs";
    }

}
