package duan2.nhom11.demo.service;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import duan2.nhom11.demo.entity.User;
import duan2.nhom11.demo.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRopository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRopository.findByEmail(email);

		String role = user.getRole();
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

		GrantedAuthority authority = new SimpleGrantedAuthority(role);

		grantList.add(authority);


        boolean enabled = user.isActive();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),enabled, accountNonExpired, //
                credentialsNonExpired, accountNonLocked, grantList);
	}

}
