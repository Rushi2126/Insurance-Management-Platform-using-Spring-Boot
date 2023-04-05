package com.rushi.insurance.models;



import java.time.LocalDate;


import javax.validation.constraints.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "insurance_policy")
public class InsurancePolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Policy number is mandatory")
	@Column(name = "policy_number", unique = true)
	private String policyNumber;

	@NotNull(message = "Policy type is mandatory")
	@Column(name = "policy_type")
	private String policyType;

	@NotNull(message = "Coverage amount is mandatory")
	@Column(name = "coverage_amount")
	private Double coverageAmount;

	@NotNull(message = "Premium is mandatory")
	@Column(name = "premium")
	private Double premium;

	@NotNull(message = "Start date is mandatory")
	@Column(name = "start_date")
	private LocalDate startDate;

	@NotNull(message = "End date is mandatory")
	@Column(name = "end_date")
	private LocalDate endDate;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	public InsurancePolicy() {
	}

	public InsurancePolicy(String policyNumber, String policyType, Double coverageAmount, Double premium,
			LocalDate startDate, LocalDate endDate) {
		super();
		this.policyNumber = policyNumber;
		this.policyType = policyType;
		this.coverageAmount = coverageAmount;
		this.premium = premium;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public Double getCoverageAmount() {
		return coverageAmount;
	}

	public void setCoverageAmount(Double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "InsurancePolicy [id=" + id + ", policyNumber=" + policyNumber + ", policyType=" + policyType
				+ ", coverageAmount=" + coverageAmount + ", premium=" + premium + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", client=" + client + "]";
	}
}
