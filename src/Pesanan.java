public class Pesanan{
	private int totalHarga;
	private String namaPesanan;
	private int totalPesanan;

	public Pesanan(String nama, int jumlah, int totalHarga) {
		this.totalHarga = totalHarga;
		this.namaPesanan = nama;
		this.totalPesanan = jumlah;
	}

	public void setTotalHarga(int totalHarga){
		this.totalHarga = totalHarga;
	}

	public int getTotalHarga(){
		return totalHarga;
	}

	public void setNamaPesanan(String namaPesanan){
		this.namaPesanan = namaPesanan;
	}

	public String getNamaPesanan(){
		return namaPesanan;
	}

	public void setTotalPesanan(int totalPesanan){
		this.totalPesanan = totalPesanan;
	}

	public int getTotalPesanan(){
		return totalPesanan;
	}

}
