public enum RedGrapes {
    rioja(
            "Rioja",
            "Rioja is made from a blend of grape varieties, with " +
                    "Tempranillo being the dominant grape. Wine from Rioja is " +
                    "known for its structure and tannins, similar to Cabernet " +
                    "Sauvignon, but it also has a fruity character. This makes " +
                    "it perfect for drinkers who love Cabernet but are also " +
                    "looking for the dominant cherry flavor."
    ),

    cotes_du_rhone(
            "Cotes du Rhône",
            "The red wines can be fruity, sweet, spicy and " +
                    "packed with ripe, red berries.The wines can be sweet, " +
                    "fruity and easy to drink."
    ),

    zinfandel(
            "Zinfandel",
            "Zinfandel (also known as Primitivo) is a " +
                    "variety of black-skinned wine grape. Red berry " +
                    "fruit flavors like raspberry predominate in " +
                    "wines from cooler areas, whereas blackberry, " +
                    "anise and pepper notes are more common in wines " +
                    "made in warmer areas and in wines made from the " +
                    "earlier-ripening Primitivo clone."
    ),

    cabernet_sauvignon(
            "Cabernet Sauvignon",
            "Cabernet sauvignon's success as a fine wine " +
                    "lies in its subtleties: secondary, complex flavours " +
                    "that have the potential to develop deliciously in " +
                    "bottle over time. A red wine designed for cellaring " +
                    "and keeping for many years. However, you can certainly " +
                    "find younger, fruitier styles to help you explore the " +
                    "flavours of this iconic grape."
    ),

    shiraz(
            "Shiraz",
            "Syrah, or shiraz as it's known in the New World, " +
                    "can produce such deliciously dark full-bodied and " +
                    "age-worthy wines. Expect rich, brooding flavours of " +
                    "blackberry, black plums and blackcurrant, with spicy, " +
                    "coffee, leathery and liquorice notes in hotter regions " +
                    "and gamey, truffle notes developing with a bit of age."
    ),

    merlot(
            "Merlot",
            "Merlot is characterised by its soft texture and " +
                    "easy, fruit-forward character. Think ripe plums, " +
                    "summer fruits and touches of vanilla and spice. It " +
                    "produces juicy, soft, plump reds, but also plays " +
                    "an important role in blends, especially with " +
                    "cabernet sauvignon."
    );


    public final String title;
    public final String description;

    RedGrapes(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
