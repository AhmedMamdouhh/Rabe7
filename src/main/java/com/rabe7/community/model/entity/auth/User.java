package com.rabe7.community.model.entity.auth;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.rabe7.community.BR;


public class User extends BaseObservable {

	@SerializedName("trader_id")
	private int traderId;

	@SerializedName("point_balance")
	private String pointBalance;

	@SerializedName("referer_id")
	private Integer refererId;

	@SerializedName("is_email_verified")
	private boolean isEmailVerified;

	@SerializedName("share_balance")
	private String shareBalance;

	@SerializedName("sub_trader_id")
	private int subTraderId;

	@SerializedName("cash_balance")
	private String cashBalance;

	@SerializedName("token")
	private String token;

	@SerializedName("user_code")
	private String userCode;

	@SerializedName("phone")
	private String phone;

	@SerializedName("coin_balance")
	private String coinBalance;

	@SerializedName("name")
	private String name;

	@SerializedName("user_type_id")
	private String userTypeId;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	@Bindable
	public int getTraderId(){
		return traderId;
	}
	@Bindable
	public String getPointBalance(){
		return pointBalance;
	}
	@Bindable
	public Integer getRefererId(){
		return refererId;
	}
	@Bindable
	public boolean isIsEmailVerified(){
		return isEmailVerified;
	}
	@Bindable
	public String getShareBalance(){
		return shareBalance;
	}
	@Bindable
	public int getSubTraderId(){
		return subTraderId;
	}
	@Bindable
	public String getCashBalance(){
		return cashBalance;
	}
	@Bindable
	public String getToken(){
		return token;
	}
	@Bindable
	public String getUserCode(){
		return userCode;
	}
	@Bindable
	public String getPhone(){
		return phone;
	}
	@Bindable
	public String getCoinBalance(){
		return coinBalance;
	}
	@Bindable
	public String getName(){
		return name;
	}
	@Bindable
	public String getUserTypeId(){
		return userTypeId;
	}
	@Bindable
	public int getId(){
		return id;
	}
	@Bindable
	public String getEmail(){
		return email;
	}


	public void setPointBalance(String pointBalance) {
		this.pointBalance = pointBalance;
		notifyPropertyChanged(BR.pointBalance);
	}
	public void setCoinBalance(String coinBalance) {
		this.coinBalance = coinBalance;
		notifyPropertyChanged(BR.coinBalance);
	}
	public void setToken(String token) {
		this.token = token;
		notifyPropertyChanged(BR.token);
	}
	public void setTraderId(int traderId) {
		this.traderId = traderId;
		notifyPropertyChanged(BR.traderId);
	}

	public void setRefererId(Integer refererId) {
		this.refererId = refererId;
		notifyPropertyChanged(BR.refererId);
	}
	public void setShareBalance(String shareBalance) {
		this.shareBalance = shareBalance;
		notifyPropertyChanged(BR.shareBalance);
	}

	public void setSubTraderId(int subTraderId) {
		this.subTraderId = subTraderId;
		notifyPropertyChanged(BR.subTraderId);
	}

	public void setCashBalance(String cashBalance) {
		this.cashBalance = cashBalance;
		notifyPropertyChanged(BR.cashBalance);
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
		notifyPropertyChanged(BR.userCode);
	}

	public void setPhone(String phone) {
		this.phone = phone;
		notifyPropertyChanged(BR.phone);
	}

	public void setName(String name) {
		this.name = name;
		notifyPropertyChanged(BR.name);
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
		notifyPropertyChanged(BR.userTypeId);
	}

	public void setId(int id) {
		this.id = id;
		notifyPropertyChanged(BR.id);
	}

	public void setEmail(String email) {
		this.email = email;
		notifyPropertyChanged(BR.email);
	}


}