/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.entities;

import com.cristianroot.springrestsecurityexample.constants.AuthorityName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Client implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private String name;

	@OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
	private List<Purchase> purchaseList;

	private String password;

	@ManyToMany
	private List<Authority> authorities;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Purchase> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<Purchase> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public void addPurchase(Purchase purchase) {
		this.purchaseList.add(purchase);
	}

	@Override
	//hay que sobreescribir las authority lo que viene de bbdd de authority pasarlo a authority con un mappeo
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities.stream()
						  .map(Authority::getName)
						  .map(AuthorityName::name)
						  .map(SimpleGrantedAuthority::new)
						  .collect(Collectors.toList());
	}

	//metodos de userDetails que hay que implementar estas cosas las tendremos a nivel de token por eso aqui marcamos todo correcto
	//y luego en el filter miramos como esta

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
