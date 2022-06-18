package com.melanich.ExchangeRates.model;

public class Gif {
    private Data data;

    public Gif(Data data) {
        this.data = data;
    }
    public Gif() {
    }

    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gif gif = (Gif) o;

        return data != null ? data.equals(gif.data) : gif.data == null;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Gif{" +
                "data=" + data +
                '}';
    }
}