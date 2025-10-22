package sum25.SE192755.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sum25.SE192755.Entity.SonyAccounts;

import java.util.List;

@Repository
public interface SonyAccountsRepository extends JpaRepository<SonyAccounts, Long> {
    public List<SonyAccounts> findAll();
    public SonyAccounts findByAccountID(Long accountID);
    public SonyAccounts findByPhoneAndPassword(String phone, String password);
}
