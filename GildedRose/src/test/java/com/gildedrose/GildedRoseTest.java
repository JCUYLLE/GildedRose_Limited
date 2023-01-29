package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void systemLowersValues() {
        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 15, 25) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(14, app.items[0].sellIn);
        assertEquals(24, app.items[0].quality);
    }
    @Test
    void qualityDegradesTwiceAsFast() {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", -1, 25) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, app.items[0].quality);
    }
    @Test
    void qualityIsNeverNegative() {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 10, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }
    @Test
    void neverChangeQualityOfSulfuras(){
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }
    @Test
    void neverChangeSellInOfSulfuras(){
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
    }
    @Test
    void LowerQualityTwiceAsFastIfSellInPassed(){
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", -1, 25)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, app.items[0].quality);
    }
    @Test
    void increaseQualityOfAgedBrieIfGettingOlder(){
        Item[] items = new Item[] { new Item("Aged Brie", 10, 25)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(26, app.items[0].quality);
    }
    @Test
    void doNotIncreaseQualityOfAgedBrieOver50(){
        Item[] items = new Item[] { new Item("Aged Brie", 10, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }
    @Test
    void lowerQualityBackstagePassesToZeroAfterConcertdate(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", -1, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }
    @Test
    void increaseQualityBackstagePasses_By1_ifSellInIsBiggerthan10(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(21, app.items[0].quality);
    }
    @Test
    void increaseQualityBackstagePasses_By2_ifSellInIsBetween10And5Days(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 8, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }
    @Test
    void increaseQualityBackstagePasses_By3_ifSellInIsInLassThan5Days(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 4, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, app.items[0].quality);
    }
    @Test
    void QualityOfBackstagePassesShouldnotGoAbove50(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 4, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

}
