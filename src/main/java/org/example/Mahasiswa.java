package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tabel_mahasiswa") // Nama tabel yang akan otomatis dibuat di hibernate_rpl
public class Mahasiswa {

    @Id // Primary Key
    @Column(name = "nim")
    private int nim;

    @Column(name = "nama_lengkap")
    private String nama;

    @Column(name = "program_studi")
    private String prodi;

    // Constructor kosong (Wajib untuk Hibernate)
    public Mahasiswa() {
    }

    // Constructor dengan parameter untuk memudahkan pengisian data
    public Mahasiswa(int nim, String nama, String prodi) {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
    }

    // Getter dan Setter
    public int getNim() { return nim; }
    public void setNim(int nim) { this.nim = nim; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getProdi() { return prodi; }
    public void setProdi(String prodi) { this.prodi = prodi; }

    @Override
    public String toString() {
        return "Mahasiswa [NIM=" + nim + ", Nama=" + nama + ", Prodi=" + prodi + "]";
    }
}