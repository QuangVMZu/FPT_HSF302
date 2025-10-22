package sum25.SE192755.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.SE192755.Entity.SonyAccounts;
import sum25.SE192755.Repository.SonyAccountsRepository;

import java.util.List;

@Service
public class SonyAccountsServiceImpl implements SonyAccountsService{

    @Autowired
    private SonyAccountsRepository sonyAccountsRepository;

    @Override
    public List<SonyAccounts> findAll() {
        return sonyAccountsRepository.findAll();
    }

    @Override
    public SonyAccounts findByAccountID(Long accountID) {
        return sonyAccountsRepository.findByAccountID(accountID);
    }

    @Override
    public void addSonyAccounts(SonyAccounts account) {
        sonyAccountsRepository.save(account);
    }

    @Override
    public SonyAccounts findByPhoneAndPassword(String phone, String password) {
        return sonyAccountsRepository.findByPhoneAndPassword(phone, password);
    }

    @Override
    public boolean isPhoneExists(String phone) {
        return sonyAccountsRepository.findAll().stream().anyMatch(acc -> acc.getPhone().equals(phone));
    }
}
