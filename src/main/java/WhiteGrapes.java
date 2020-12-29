public enum WhiteGrapes {

    chardonnay(
            "Chardonnay",
            "The grape itself is relatively neutral, with fresh appley" +
                    "aromas, but techniques such as oak ageing (which adds vanilla, " +
                    "toast or toffee aromas), malolactic fermentation and lees " +
                    "stirring all add different layers of flavour and complexity."
    ),

    sauvignon_blanc(
            "Sauvignon Blanc",
            "In general, sauvignon blanc is used to make dry wines, " +
                    "distinctive for their fresh acidity and beautiful perfume. " +
                    "In cooler climates the wines often have bright citrus and " +
                    "mineral notes combined with a green nettle character. " +
                    "Oak ageing, lees stirring and malolactic fermentation " +
                    "can also produce sauvignons that are rounded rich and full."
    ),

    pinot_grigio(
            "Pinot Grigio",
            "This grape is charmingly light and fresh with " +
                    "citrus, peach and floral aromas. It's brilliant drunk on " +
                    "its own as an aperitif or with salads and creamy cheeses." +
                    "Everyday pinot grigios are a great place to start for simple, " +
                    "fresh, fruity white wines for easy drinking."
    ),

    gewurztraminer(
            "Gewürztraminer",
            "Expect scents of sweet spice, plump pink roses, " +
                    "lychees, ginger and hints of grapefruit and white peach. " +
                    "Gewürz is also often the answer when it comes to hard-to-match " +
                    "spicy dishes like Thai food, aromatic Indian dishes, Vietnamese, " +
                    "or Chinese, basically anything with fragrance and a mild-medium " +
                    "chilli hit."
    ),

    chenin_blanc(
            "Chenin Blanc",
            "The aromas and flavor notes include the descriptors of " +
                    "minerally, greengage, angelica and honey. Chenin wines produced " +
                    "from noble rot will have notes of peaches and honey that " +
                    "develop into barley sugar, marzipan, and quince as they age. " +
                    "Dry or semi-sweet Chenin blanc from the Loire will often have " +
                    "notes apple, greengage, and chalky minerals that develop into " +
                    "more honey, acacia, and quince aromas."
    ),

    riesling("Riesling",
            "Riesling is a bit of an enigma; ethereal, floral and " +
                    "enchanting, riesling is also one of the world's hardiest grape " +
                    "varieties, with a high level of frost resistance meaning it " +
                    "thrives in some of the coolest climate wine regions. This " +
                    "aromatic grape variety makes some of the world's finest white " +
                    "wines in a vast variety of styles, the best of which will age " +
                    "brilliantly, turning from floral, lime aromas and flavours to " +
                    "intriguing developed aromas of kerosene."
    );

    public final String title;
    public final String description;

    WhiteGrapes(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
