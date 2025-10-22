package sum25.SE192755.Service;

import org.springframework.stereotype.Service;
import sum25.SE192755.Entity.SonyAccounts;

import java.util.List;

@Service
public interface SonyAccountsService {
    public List<SonyAccounts> findAll();
    public SonyAccounts findByAccountID(Long accountID);
    public void addSonyAccounts(SonyAccounts accounts);
    public SonyAccounts findByPhoneAndPassword(String phone, String password);
    public boolean isPhoneExists(String phone);
}
