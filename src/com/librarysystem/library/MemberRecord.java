package com.librarysystem.library;

import java.time.LocalDate;
    public class MemberRecord {
        private Long memberId;
        private MemberType memberType;
        private LocalDate dateOfMemberShip;
        private int maxBookLimit;
        private int booksIssued;
        private String name;
        private String address;
        private String phoneNumber;

        public MemberRecord(Long memberId, MemberType memberType, LocalDate dateOfMemberShip, int maxBookLimit, int booksIssued, String name, String address, String phoneNumber) {
            this.memberId = memberId;
            this.memberType = memberType;
            this.dateOfMemberShip = dateOfMemberShip;
            this.maxBookLimit = maxBookLimit;
            this.booksIssued = 0; //başlangıçta ödünc alınan kitap yo
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
        }

        public String getMember(){
            return "Üye: " + name + " | Kitap sayısı: " + maxBookLimit;
        }

        public MemberType getMemberType() {
            return memberType;
        }



        // Ödünç alınan kitap sayısını artırır
        public void incBookIssued() {
            if (booksIssued < maxBookLimit) {
                booksIssued++;
            } else {
                System.out.println(name + " daha fazla kitap ödünç alamaz! Limit doldu.");
            }
        }

        // Ödünç alınan kitap sayısını azaltır
        public void decBookIssued() {
            if (booksIssued > 0) {
                booksIssued--;
            }
        }

        // Fatura ödeme işlemi
        public void payBill(double amount) {
            System.out.println(name + " " + amount + " TL ödeme yaptı.");
        }
    }


