package com.jp319.zeroartfetcher.testing;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ZerochanResponseParser {

    public static String extractJson(String input) {
        int startIndex = input.indexOf("\"items\": [");
        if (startIndex >= 0) {
            int endIndex = input.lastIndexOf("]");
            if (endIndex >= startIndex) {
                return input.substring(startIndex, endIndex + 1);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String input = "{\n" +
                "<div style=\"text-align: center; margin: 20px 0; \">\n" +
                "<style>\n" +
                ".Zerochan_MOINSBD_Top { width: 320px; height: 50px; }\n" +
                "@media(min-width: 500px) { .Zerochan_MOINSBD_Top { width: 468px; height: 60px; } }\n" +
                "@media(min-width: 800px) { .Zerochan_MOINSBD_Top { width: 728px; height: 200px; } }\n" +
                "@media(min-width: 1300px) { .Zerochan_MOINSBD_End { width: auto; height: 220px; } }\n" +
                "</style>\n" +
                "<script async src=\"https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js?client=ca-pub-3000346950361353\" crossorigin=\"anonymous\"></script>\n" +
                "<!-- Zerochan_MOINSBD_Top -->\n" +
                "<ins class=\"adsbygoogle Zerochan_MOINSBD_Top\"\n" +
                "     style=\"display:inline-block\"\n" +
                "     data-ad-client=\"ca-pub-3000346950361353\"\n" +
                "     data-ad-slot=\"3078685876\"></ins>\n" +
                "<script>\n" +
                "(adsbygoogle = window.adsbygoogle || []).push({overlays: {bottom: true}});\n" +
                "</script></div>\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"id\": 3982336,\n" +
                "      \"width\": 2500,\n" +
                "      \"height\": 2425,\n" +
                "      \"thumbnail\": \"https://s3.zerochan.net/240/36/46/3982336.jpg\",\n" +
                "      \"source\": \"\",\n" +
                "      \"tag\": \"Genshin Impact\",\n" +
                "      \"tags\":\n" +
                "      [\n" +
                "        \"Female\",\n" +
                "        \"Male\",\n" +
                "        \"Fanart\",\n" +
                "        \"Flower\",\n" +
                "        \"Dress\",\n" +
                "        \"Water\",\n" +
                "        \"Short Hair\",\n" +
                "        \"Gloves\",\n" +
                "        \"Bow (Weapon)\",\n" +
                "        \"Blonde Hair\",\n" +
                "        \"Orange Hair\",\n" +
                "        \"Sword\",\n" +
                "        \"Mask\",\n" +
                "        \"Crystal\",\n" +
                "        \"Jewelry\",\n" +
                "        \"Weapons\",\n" +
                "        \"Gold Eyes\",\n" +
                "        \"Archery\",\n" +
                "        \"Hair Flower\",\n" +
                "        \"Pixiv\",\n" +
                "        \"Suit\",\n" +
                "        \"Scarf\",\n" +
                "        \"Hair Ornament\",\n" +
                "        \"Gemstone\",\n" +
                "        \"Duo\",\n" +
                "        \"Formal\",\n" +
                "        \"Long Sleeves\",\n" +
                "        \"Black Gloves\",\n" +
                "        \"Stars (Sky)\",\n" +
                "        \"Fanart from Pixiv\",\n" +
                "        \"Holding Weapon\",\n" +
                "        \"Black Handwear\",\n" +
                "        \"Sakon04\",\n" +
                "        \"Genshin Impact\",\n" +
                "        \"Lumine\",\n" +
                "        \"Tartaglia\",\n" +
                "        \"Short Hair with Long Sidelocks\",\n" +
                "        \"Holding Sword\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3981902,\n" +
                "      \"width\": 1240,\n" +
                "      \"height\": 1740,\n" +
                "      \"thumbnail\": \"https://s3.zerochan.net/240/02/38/3981902.jpg\",\n" +
                "      \"source\": \"\",\n" +
                "      \"tag\": \"Genshin Impact\",\n" +
                "      \"tags\":\n" +
                "      [\n" +
                "        \"Female\",\n" +
                "        \"Ecchi\",\n" +
                "        \"Fanart\",\n" +
                "        \"Flower\",\n" +
                "        \"Dress\",\n" +
                "        \"Sleepwear\",\n" +
                "        \"White Dress\",\n" +
                "        \"Blonde Hair\",\n" +
                "        \"Room\",\n" +
                "        \"Bows (Fashion)\",\n" +
                "        \"Two Girls\",\n" +
                "        \"Gold Eyes\",\n" +
                "        \"Sunbeam\",\n" +
                "        \"Hair Flower\",\n" +
                "        \"Pixiv\",\n" +
                "        \"Alternate Outfit\",\n" +
                "        \"Hair Ornament\",\n" +
                "        \"Duo\",\n" +
                "        \"Medium Hair\",\n" +
                "        \"Indoors\",\n" +
                "        \"Fanart from Pixiv\",\n" +
                "        \"Cleavage\",\n" +
                "        \"chabing0802\\\\\",\n" +
                "        \"Genshin Impact\",\n" +
                "        \"Lumine\",\n" +
                "        \"Yoimiya\",\n" +
                "        \"Short Hair with Long Sidelocks\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";

        // Extract the JSON part from the input
        String json = "{\n" + extractJson(input) + "\n}";

        // Print the extracted JSON
        if (json != null) {
            System.out.println(json);

            // Parse the JSON using Gson to access its elements
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            System.out.println(jsonObject.toString());
//            System.out.println("id: " + jsonObject.get("items").getAsJsonArray().get(0).getAsJsonObject().get("id"));
//            System.out.println("width: " + jsonObject.get("items").getAsJsonArray().get(0).getAsJsonObject().get("width"));
            // Add more fields as needed
        } else {
            System.out.println("JSON not found in the input.");
        }
    }
}

