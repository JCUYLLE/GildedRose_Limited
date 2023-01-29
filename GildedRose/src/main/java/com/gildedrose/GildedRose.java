package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }
    /**
     * for loop that goes over every item in the list items
     * and calls the method updateItem for every item.
     */
    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateItem(items[i]);
        }
    }

    /**
     *Updates both the Quality and the SellIn value for all the items.
     */
    private void updateItem(Item item) {
        updateQuality(item);
        updateSellIn(item);
    }

    /**
     * this method will check wether on certain items since they have divergent rules.
     *
     */
    private void updateQuality(Item item) {
        if (isAgedBrie(item)) {
            increaseQuality(item);
        } else if (isBackstagePass(item)) {
            updateBackstagePassQuality(item);
        } else if (!isSulfuras(item)) {
            decreaseQuality(item);
        }
    }

    /**
     * Will check if an item is not Sulfuras to decrease sellin date since sulfuras does not expire
     *
     */
    private void updateSellIn(Item item) {
        if (!isSulfuras(item)) {
            item.sellIn--;
        }
    }

    /**
     * Will increase Quality but only if it is not higher than 50
     */
    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }
    /**
     * Backstage passes increase in quality with different values
     * the shorter to the concert date, the bigger the increase value
     * less than 50 +1
     * if less than 10 +2
     * if less than 6 +3
     */
    private void updateBackstagePassQuality(Item item) {
        if (item.sellIn < 0) {
            item.quality = 0;
        } else {
            if (item.quality < 50) {
                item.quality++;
            }
            if (item.sellIn < 10 && item.quality < 50) {
                item.quality++;
            }
            if (item.sellIn < 5 && item.quality < 50) {
                item.quality++;
            }
        }
    }
    /**
     * Will decrease Quality but only if quality is bigger than 0 and sellin is below 0.
     */
    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
        if (item.sellIn < 0 && item.quality > 0) {
            item.quality--;
        }
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }
}