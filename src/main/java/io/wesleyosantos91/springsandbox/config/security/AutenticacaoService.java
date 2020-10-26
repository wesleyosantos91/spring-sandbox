package io.wesleyosantos91.springsandbox.config.security;

import io.wesleyosantos91.springsandbox.exception.core.ObjectNotFoundException;
import io.wesleyosantos91.springsandbox.model.entity.Usuario;
import io.wesleyosantos91.springsandbox.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository repository;

    public AutenticacaoService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByEmail(username);

        if (usuario.isPresent()) {
            return usuario.get();
        }

       throw new ObjectNotFoundException(format("Usuário com username %s não encontrado", username));
    }
}
