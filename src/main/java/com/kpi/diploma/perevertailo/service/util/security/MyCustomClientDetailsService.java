//package com.kpi.diploma.perevertailo.service.util.security;
//
//import com.kpi.diploma.perevertailo.model.document.user.User;
//import com.kpi.diploma.perevertailo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.provider.ClientDetails;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.ClientRegistrationException;
//import org.springframework.security.oauth2.provider.NoSuchClientException;
//import org.springframework.security.oauth2.provider.client.BaseClientDetails;
//
//import java.util.Optional;
//
//public class MyCustomClientDetailsService implements ClientDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
//
//        Optional<User> user = userRepository.findById(clientId);
//
//        if (!user.isPresent()) {
//            throw new NoSuchClientException("No client with requested id: " + clientId);
//        }
//
//        BaseClientDetails details = new BaseClientDetails(clientId, "resourceIdTest", "write", "client_credentials", "USER");
//
//        details.setClientSecret(fan.getEncodedPassword());
//
//        return details;
//    }
//}
