package com.ustodo.utilg

import java.text.Normalizer;


/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 5/3/13
 * Time: 6:41 PM
 * To change this template use File | Settings | File Templates.
 */


class UtilRegExJavaNormalizer {

    // see http://www.drillio.com/en/software-development/java/removing-accents-diacritics-in-any-language/
    // String original = "aáeéiíoóöőuúüű AÁEÉIÍOÓÖŐUÚÜŰ";
    public static String removeAccents( text) {
        return text == null ? null
        : Normalizer.normalize(text, Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public static String removeAccents2( text) {
        return text == null ? null
        : Normalizer.normalize(text, Normalizer.Form.NFKD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public static String removeAccents3( text, form) {
        return text == null ? null
        : Normalizer.normalize(text, form)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

            Normalizer.Form.NFD
    }




    public static void main(String[] args) {
        String weirdDash = "–";
        String normalDash = "-";

        System.out.println ("weirdDash:" + Character.digit(weirdDash.toCharArray()[0], 16));
        System.out.println ("normalDash:" + Character.digit(normalDash.toCharArray()[0], 16));

        boolean b = Normalizer.isNormalized(weirdDash, Normalizer.Form.NFD);
        if(b == false) {
            System.out.println("Java thinks the weird dash is normal");
            return;
        }
        String normalizedWeirdDash = Normalizer.normalize(weirdDash, Normalizer.Form.NFD);

        if(normalizedWeirdDash.equals(normalDash)) {
            System.out.println("Yay!");
        } else {
            System.out.println("Boo! normalized weird dash "+(normalizedWeirdDash.equals(weirdDash) ? "didn't change" : "= " + normalizedWeirdDash));
        }
    }


}
