package com.vsh8k.mushop.model.Shop;

import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
@Getter
@Setter
public final class Vinyl extends Media{
    public enum VinylSpeed {
        SPEED_33(33),
        SPEED_45(45),
        SPEED_78(78);

        private int value;

        VinylSpeed(int value) {
            this.value = value;
        }

        public String getName() {
            return name();
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return switch (this) {
                case SPEED_33 -> "33 1/3 RPM";
                case SPEED_45 -> "45 RPM";
                case SPEED_78 -> "78 RPM";
            };
        }
}
    VinylSpeed vinylSpeed;
    public Vinyl(int id, String title, String description, int qty, float weight, float price, int discount, String artist, String album, int releaseYear, String label, Time totalLen, short trackQty, String mediaGrade, String sleeveGrade, String genre, String ean, String mediaType, String imageURL, VinylSpeed vinylSpeed) {
        super(id, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, imageURL);//TRN
        this.vinylSpeed = vinylSpeed;
    }
}
