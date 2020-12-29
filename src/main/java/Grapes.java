import java.util.ArrayList;
import java.util.List;

public enum Grapes {
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
    ),

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

    Grapes(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
