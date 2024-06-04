package com.javaweb.function;

public class ConvertToSlug {
	 public static String convertToSlug(String input) {
	        // Chuyển đổi sang chữ thường
	        String slug = input.toLowerCase();
	        // Loại bỏ dấu
	        slug = slug.replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a");
	        slug = slug.replaceAll("[èéẹẻẽêềếệểễ]", "e");
	        slug = slug.replaceAll("[ìíịỉĩ]", "i");
	        slug = slug.replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o");
	        slug = slug.replaceAll("[ùúụủũưừứựửữ]", "u");
	        slug = slug.replaceAll("[ỳýỵỷỹ]", "y");
	        slug = slug.replaceAll("[đ]", "d");
	        slug = slug.replaceAll("\\s+", "-");
	        // Loại bỏ các ký tự không phải chữ cái, số, hoặc dấu gạch ngang
	        slug = slug.replaceAll("[^a-z0-9-]", "");
	        // Loại bỏ dấu gạch ngang ở đầu và cuối chuỗi
	        slug = slug.replaceAll("^-|-$", "");
	        return slug;
	    }
}
