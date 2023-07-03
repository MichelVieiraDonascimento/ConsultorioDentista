package com.dh.projetoIntegrador.service;

import com.dh.projetoIntegrador.annotation.IntegrationTest;
import com.dh.projetoIntegrador.configuracao.BatatinhaConfiguration;
import com.dh.projetoIntegrador.dto.response.CadastrarConsultaResponseDTO;
import com.dh.projetoIntegrador.dto.response.ConsultaResponseDTO;
import com.dh.projetoIntegrador.dto.response.DentistaResponseDTO;
import com.dh.projetoIntegrador.dto.response.PacienteResponseDTO;
import com.dh.projetoIntegrador.model.Consulta;
import com.dh.projetoIntegrador.model.Dentista;
import com.dh.projetoIntegrador.model.Endereco;
import com.dh.projetoIntegrador.model.Paciente;
import com.dh.projetoIntegrador.repository.ConsulaRepository;
import com.dh.projetoIntegrador.repository.DentistaRepository;
import com.dh.projetoIntegrador.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.logging.Logger;

@ContextConfiguration(classes = BatatinhaConfiguration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class ProjetoIntegradorApplicationTests {

    @Autowired
    public  DentistaService dentistaService;
    @Autowired
    public  DentistaRepository dentistaRepository;
    @Autowired
    public  PacienteService pacienteService;
	@Autowired
	public  PacienteRepository pacienteRepository;

    @Autowired
    public  ConsultaService consultaService;
    @Autowired
    public ConsulaRepository consulaRepository;

    @Autowired
    public ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;


//    private static final Logger logger = Logger.getLogger(p)



                               // Não foi necessário ultilizar
    @BeforeAll
    void psodsd() throws Exception {


        Endereco endereco = new Endereco("campo grnde",
                "mato grosso do sul", "vila nasser", "lindoia", 25L, "4694984897");
        LocalDate date = LocalDate.parse("2023-05-05");
        PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO("michel",
                "VIeiraTest", endereco, "15365874506", date);
        var paciente = new Paciente(pacienteResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/paciente")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(paciente)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect((MockMvcResultMatchers.status().isCreated()));
//        pacienteRepository.save(paciente);
//
//
        var dentista = new Dentista(new DentistaResponseDTO("nome", "sdas", "123456789"));

        mockMvc.perform(MockMvcRequestBuilders.post("/dentista")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dentista)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect((MockMvcResultMatchers.status().isCreated()));
//        dentistaRepository.save(dentista);


            var cadastrarConsultaResponseDTO = new CadastrarConsultaResponseDTO("15365874506", "123456789", "2023-06-20 13:18:46");

            Optional<Dentista> optionalDentista = dentistaRepository.findJogadorByMatricula(cadastrarConsultaResponseDTO.getMatricula());
            System.out.println(optionalDentista);
            Optional<Paciente> optionalPaciente = pacienteRepository.findJogadorByRgContainingIgnoreCase(cadastrarConsultaResponseDTO.getRg());
            System.out.println(optionalPaciente);
            System.out.println("aaaaaaaaaaaaaaaaa" + optionalDentista.get());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateHora = LocalDateTime.parse(cadastrarConsultaResponseDTO.getDataHora(), formatter);

            ConsultaResponseDTO consultaDTO = new ConsultaResponseDTO();

            consultaDTO.setDentista(optionalDentista.get());
            consultaDTO.setPaciente(optionalPaciente.get());
            consultaDTO.setDataHora(dateHora);

            String consultaSalva = consultaService.salvar(new Consulta(consultaDTO));

        }



                           //	DENTISTA


      // Cadastrando um dentista!
//	@Test
//	@DisplayName("cadastrando um dentista")
//	void cadastrarDentistas() throws Exception {
//		var dentista = new Dentista(new DentistaResponseDTO("Leandro", "Finotti", "123456789"));
//
//
//		mockMvc.perform(MockMvcRequestBuilders.post("/dentista")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(dentista)))
//				.andDo(MockMvcResultHandlers.print())
//				.andExpect((MockMvcResultMatchers.status().isCreated()));
//	}

    // Buscando todos os dentistas!
    @Test
    @DisplayName("Buscar todos os dentistar")
    void buscarTodosDentistas() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/dentista/listar"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect((MockMvcResultMatchers.status().isOk()));
    }





                             // PACIENTE

  // Cadastrando um paciente!
//	@Test
//	@DisplayName("cadastrando um paciente")
//	void cadastrarPaciente() throws Exception {
//
//		var paciente = new Paciente(new PacienteResponseDTO("Michel","Vieira",new Endereco("campo grnde",
//				"mato grosso do sul","vila nasser","lindoia",25L, "4694984897"), "15365874506", LocalDate.parse("2023-05-05")));
//
//
//
//		mockMvc.perform(MockMvcRequestBuilders.post("/paciente")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsBytes(paciente)))
//				.andDo(MockMvcResultHandlers.print())
//				.andExpect((MockMvcResultMatchers.status().isCreated()));
//	}


     // Buscando todos os dentistas!
    @Test
    @DisplayName("Buscar todos os pacientes")
    void buscarTodosPacientes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/listar"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect((MockMvcResultMatchers.status().isOk()));
    }



                                // CONSULTA


      // Cadastrando uma consulta!
//    @Test
//    @DisplayName("Criando uma consulta")
//    void cadastrarConsulta() throws Exception {
//        var cadastrarConsultaResponseDTO = new CadastrarConsultaResponseDTO("15365874506", "123456789", "2023-06-20 13:18:46");
//
//        Optional<Dentista> optionalDentista = dentistaRepository.findJogadorByMatricula(cadastrarConsultaResponseDTO.getMatricula());
//        System.out.println(optionalDentista);
//        Optional<Paciente> optionalPaciente = pacienteRepository.findJogadorByRgContainingIgnoreCase(cadastrarConsultaResponseDTO.getRg());
//        System.out.println(optionalPaciente);
//        System.out.println("aaaaaaaaaaaaaaaaa" + optionalDentista.get());
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime date = LocalDateTime.parse(cadastrarConsultaResponseDTO.getDataHora(), formatter);
//
//        ConsultaResponseDTO consultaDTO = new ConsultaResponseDTO();
//
//        consultaDTO.setDentista(optionalDentista.get());
//        consultaDTO.setPaciente(optionalPaciente.get());
//        consultaDTO.setDataHora(date);
//
//        String consultaSalva = consultaService.salvar(new Consulta(consultaDTO));
//
//        // COM O mockMvc NÃO FOI DE JEITO NENHUM!!!!!!!!!!!
//
////        mockMvc.perform(MockMvcRequestBuilders.post("/consulta")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(objectMapper.writeValueAsBytes(consultaDTO)))
////                .andDo(MockMvcResultHandlers.print())
////                .andExpect((MockMvcResultMatchers.status().isCreated()));
//    }

      //Buscanto todas as consultas
      @Test
      @DisplayName("Buscar todos as consultas")
      void buscarTodosConsultas() throws Exception {
          mockMvc.perform(MockMvcRequestBuilders.get("/consulta/listar"))
                  .andDo(MockMvcResultHandlers.print())
                  .andExpect((MockMvcResultMatchers.status().isOk()));
      }



    @AfterAll
     void Deletar() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse("2023-06-20 13:18:46", formatter);
        Optional<Consulta> optionalConsulta = consulaRepository.findByDataHora(date);
        consultaService.excluir(optionalConsulta.get().getId());


        Optional<Paciente> idPaciente = pacienteRepository.findJogadorByRgContainingIgnoreCase("15365874506");


//        mockMvc.perform(MockMvcRequestBuilders.delete("/deletar/{id}", idPaciente.get().getId()))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect((MockMvcResultMatchers.status().isOk()));
        pacienteService.excluir (idPaciente.get().getId());

        Optional<Dentista> optionalDentista = dentistaRepository.findJogadorByMatricula("123456789");
          dentistaService.excluir(optionalDentista.get().getId());

    }


}
