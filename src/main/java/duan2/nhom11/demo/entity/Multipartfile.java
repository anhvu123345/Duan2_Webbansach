package duan2.nhom11.demo.entity;

import org.springframework.web.multipart.MultipartFile;

public class Multipartfile {
	private MultipartFile[] filea;
	private MultipartFile[] fileb;
	private MultipartFile[] filec;
	private MultipartFile[] filed;

	public Multipartfile(MultipartFile[] filea, MultipartFile[] fileb, MultipartFile[] filec, MultipartFile[] filed) {
		super();
		this.filea = filea;
		this.fileb = fileb;
		this.filec = filec;
		this.filed = filed;
	}

	public MultipartFile[] getFilea() {
		return filea;
	}

	public void setFilea(MultipartFile[] filea) {
		this.filea = filea;
	}

	public MultipartFile[] getFileb() {
		return fileb;
	}

	public void setFileb(MultipartFile[] fileb) {
		this.fileb = fileb;
	}

	public MultipartFile[] getFilec() {
		return filec;
	}

	public void setFilec(MultipartFile[] filec) {
		this.filec = filec;
	}

	public MultipartFile[] getFiled() {
		return filed;
	}

	public void setFiled(MultipartFile[] filed) {
		this.filed = filed;
	}
}
