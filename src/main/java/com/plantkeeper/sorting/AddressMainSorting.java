package com.plantkeeper.sorting;

import java.util.Comparator;

import com.plantkeeper.dto.AddressDTO;

public class AddressMainSorting implements Comparator<AddressDTO>{

	public int compare(AddressDTO a1, AddressDTO a2) {
		return a2.getIsMain().compareTo(a1.getIsMain());
	}
}

