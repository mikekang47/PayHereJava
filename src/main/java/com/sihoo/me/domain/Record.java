package com.sihoo.me.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Record {
	@Id
	@GeneratedValue
	private Long id;

	private String body;

	private int money;

	private boolean isDeleted;

	public static Record createRecord(String body, int money) {
		return Record.builder()
			.body(body)
			.money(money)
			.build();
	}

	public void updateRecord(String body, int money) {
		this.body = body;
		this.money = money;
	}

	public void deleteRecord() {
		this.isDeleted = true;
	}
}
