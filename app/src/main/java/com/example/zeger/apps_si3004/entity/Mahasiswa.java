package com.example.zeger.apps_si3004.entity;

/**
 * Created by zeger on 01/04/17.
 */

public class Mahasiswa {
    private long id;
    private String nim;
    private String nama;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
}
