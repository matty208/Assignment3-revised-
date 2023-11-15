package edu.monmouth.book;

import java.util.Objects;

public class Book {
	
	private int numberOfPages;
	private BookTypes bookType;
	private String title;
	private double price;
	
	
	public Book(int numberOfPages, double price, String title, BookTypes bookType) throws BookException {
		
		setNumberOfPages(numberOfPages);
		setBookType(bookType);
		setTitle(title);
		setPrice(price);
	}
	
	public int getNumberOfPages() {
		return numberOfPages;
	}
	
	public void setNumberOfPages(int numberOfPages) throws BookException {
		if (numberOfPages < BookConstants.MIN_PAGES ) {
			throw new BookException("Number of pages cannot be less than" + BookConstants.MIN_PAGES);
		}
		this.numberOfPages = numberOfPages;
	}
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) throws BookException {
		if (title == null || title.length()==0) {
			throw new BookException("Title cannot be null or have zero length");
		}
		this.title = title;
	}
	
	public BookTypes getBookType() {
        return bookType;
    }

    public void setBookType(BookTypes bookType) {
        this.bookType = bookType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws BookException {
        if (price < BookConstants.MIN_PRICE) {
            throw new BookException("Price cannot be negative");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Type: " + bookType + ", Pages: " + numberOfPages + ", Price: " + price;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Book other = (Book) obj;
        return numberOfPages == other.numberOfPages &&
               title.equals(other.title) &&
               bookType == other.bookType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, numberOfPages, bookType);
    }
}



