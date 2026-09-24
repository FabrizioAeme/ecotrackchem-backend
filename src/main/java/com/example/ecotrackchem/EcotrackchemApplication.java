package com.example.ecotrackchem;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.ecotrackchem.model.InsumoQuimico;
import com.example.ecotrackchem.model.Usuario;
import com.example.ecotrackchem.repository.InsumoQuimicoRepository;
import com.example.ecotrackchem.repository.UsuarioRepository;
import com.example.ecotrackchem.util.NivelPeligrosidad;
import com.example.ecotrackchem.util.RolUsuario;

@SpringBootApplication
public class EcotrackchemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcotrackchemApplication.class, args);
	}

	//Datos de prueba de arranque de la aplicacion EckoTrackChem, se crea un usuario administrador y un insumo quimico de prueba
	@Bean
	CommandLineRunner commandLineRunner(
			UsuarioRepository usuarioRepository,
			InsumoQuimicoRepository insumoRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			if (usuarioRepository.findByCorreo("admin@ecotrackchem.com").isEmpty()) {
				var admin = Usuario.builder()
						.nombres("Admin")
						.apellidos("EcoTrackChem")
						.correo("admin@ecotrackchem.com")
						.clave(passwordEncoder.encode("password123"))
						.rol(RolUsuario.ADMINISTRADOR)
						.build();
				usuarioRepository.save(admin);
			}

			if (insumoRepository.findByCodigoCas("7732-18-5").isEmpty()) {
				var agua = InsumoQuimico.builder()
						.codigoCas("7732-18-5")
						.nombre("Agua desionizada")
						.nivelPureza(new BigDecimal("99.90"))
						.nivelPeligrosidad(NivelPeligrosidad.NEUTRO)
						.stockDisponible(new BigDecimal("500.00"))
						.build();
				insumoRepository.save(agua);
			}
		};
	}
}
