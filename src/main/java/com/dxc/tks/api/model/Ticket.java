package com.dxc.tks.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketid;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CREATEDBY")
	private String createdBy;

	@Column(name = "OWNEDBY")
	private String ownedBy;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "PRICE")
	private String price;

}