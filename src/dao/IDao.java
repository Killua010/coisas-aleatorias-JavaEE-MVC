package dao;

import java.util.List;

import domain.DomainEntity;

public interface IDao <domain extends DomainEntity> {
	public void create(domain domain);
	public List<domain> read(domain domain);
//	public List<domain> findBy(List<String> tableValues, List<String> result);
	public void update(domain domain);
	public void delete(domain domain);
}
