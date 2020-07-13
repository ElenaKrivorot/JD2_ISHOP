package by.krivorot.ishop.entity;

import java.io.Serializable;

public class FullProduct implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String author;
	private int price;
	private int amount;
	private String image;
	private String annotation;
	private int year;
	private int pages;
		
	public FullProduct() {
		
	}
	
	public FullProduct(int id, String name, String author, int price, int amount, String image, String annotation,
			int year, int pages) {
	
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.amount = amount;
		this.image = image;
		this.annotation = annotation;
		this.year = year;
		this.pages = pages;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((annotation == null) ? 0 : annotation.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + id;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + pages;
		result = prime * result + price;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FullProduct other = (FullProduct) obj;
		if (amount != other.amount)
			return false;
		if (annotation == null) {
			if (other.annotation != null)
				return false;
		} else if (!annotation.equals(other.annotation))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (id != other.id)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pages != other.pages)
			return false;
		if (price != other.price)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FullProduct [id=" + id + ", name=" + name + ", author=" + author + ", price=" + price + ", amount="
				+ amount + ", image=" + image + ", annotation=" + annotation + ", year=" + year + ", pages=" + pages
				+ "]";
	}
	
}
