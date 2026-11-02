package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Memuat konfigurasi Hibernate dan mendaftarkan class Mahasiswa
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Mahasiswa.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // --- 1. CREATE (Menyimpan Data Baru) ---
            System.out.println("=== 1. PROSES CREATE DATA ===");
            Mahasiswa mhs1 = new Mahasiswa(101, "Johan Muhammad", "Teknik Informatika");
            Mahasiswa mhs2 = new Mahasiswa(102, "Sandi Firmansyah", "Sistem Informasi");
            session.persist(mhs1);
            session.persist(mhs2);
            tx.commit();
            System.out.println("Berhasil menyimpan 2 data mahasiswa!\n");

            // --- 2. READ (Membaca Data Berdasarkan NIM) ---
            System.out.println("=== 2. PROSES READ DATA ===");
            Mahasiswa dataCari = session.find(Mahasiswa.class, 101);
            System.out.println("Data ditemukan: " + dataCari + "\n");

            // --- 3. UPDATE (Mengubah Data) ---
            System.out.println("=== 3. PROSES UPDATE DATA ===");
            tx = session.beginTransaction();
            dataCari.setProdi("Rekayasa Perangkat Lunak"); // Ubah prodi Budi
            session.merge(dataCari);
            tx.commit();
            System.out.println("Data NIM 101 berhasil diupdate menjadi: " + dataCari + "\n");

            // --- 4. DELETE (Menghapus Data) ---
            // Catatan: Saat demo pertama kali, bagian Delete di bawah ini bisa kamu komen (//)
            // supaya datanya tetap terlihat di phpMyAdmin.
            /*
            System.out.println("=== 4. PROSES DELETE DATA ===");
            tx = session.beginTransaction();
            Mahasiswa dataHapus = session.find(Mahasiswa.class, 102);
            if (dataHapus != null) {
                session.remove(dataHapus);
                System.out.println("Data NIM 102 berhasil dihapus!");
            }
            tx.commit();
            */

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}