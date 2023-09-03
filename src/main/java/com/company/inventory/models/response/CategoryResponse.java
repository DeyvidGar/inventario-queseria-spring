package com.company.inventory.models.response;

import com.company.inventory.models.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryResponse {

    List<Category> categories;

}
