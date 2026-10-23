package NeighborhoodLibrary;

public class Book {

    private int id;
    private String isbn;
    private String title;
    private boolean isCheckedOut;
    private String checkedOutTo;

        public int getId () {
            return id;
        }

        public String getIsbn () {
            return isbn;
        }

        public String getTitle () {
            return title;
        }

        public boolean isCheckedOut () {
            return isCheckedOut;
        }

        public void setIsCheckedOut ( boolean isCheckedOut){
            this.isCheckedOut = isCheckedOut;
        }

        public String getCheckedOutTo () {
            return checkedOutTo;
        }

        public void setCheckedOutTo (String checkedOutTo){
            this.checkedOutTo = checkedOutTo;
        }

        public Book(int id, String isbn, String title, boolean isCheckedOut, String checkedOutTo) {
            this.id = id;
            this.isbn = isbn;
            this.title = title;
            this.isCheckedOut = isCheckedOut;
            this.checkedOutTo = checkedOutTo;

            }
        }
