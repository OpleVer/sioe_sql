package com.oplever.sioe.web.rest;

import com.oplever.sioe.SioeSqlApp;

import com.oplever.sioe.domain.Peticion;
import com.oplever.sioe.domain.Origen;
import com.oplever.sioe.domain.Peticionario;
import com.oplever.sioe.repository.PeticionRepository;
import com.oplever.sioe.service.PeticionService;
import com.oplever.sioe.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.oplever.sioe.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PeticionResource REST controller.
 *
 * @see PeticionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SioeSqlApp.class)
public class PeticionResourceIntTest {

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE_SOLICITANTE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_SOLICITANTE = "BBBBBBBBBB";

    private static final String DEFAULT_PATERNO_SOLICITANTE = "AAAAAAAAAA";
    private static final String UPDATED_PATERNO_SOLICITANTE = "BBBBBBBBBB";

    private static final String DEFAULT_MATERNO_SOLICITANTE = "AAAAAAAAAA";
    private static final String UPDATED_MATERNO_SOLICITANTE = "BBBBBBBBBB";

    private static final String DEFAULT_CARGO_SOLICITANTE = "AAAAAAAAAA";
    private static final String UPDATED_CARGO_SOLICITANTE = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION_SOLICITANTE = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION_SOLICITANTE = "BBBBBBBBBB";

    private static final String DEFAULT_ACTO_CONSTAR = "AAAAAAAAAA";
    private static final String UPDATED_ACTO_CONSTAR = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_FECHA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FECHA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final byte[] DEFAULT_OFICIO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_OFICIO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_OFICIO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_OFICIO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_RESPONSABLE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSABLE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_STATUS_PREVENCION = false;
    private static final Boolean UPDATED_STATUS_PREVENCION = true;

    private static final byte[] DEFAULT_OFICIO_PREVENCION = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_OFICIO_PREVENCION = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_OFICIO_PREVENCION_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_OFICIO_PREVENCION_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_NOTIFICACION_PREVENCION = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_NOTIFICACION_PREVENCION = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_NOTIFICACION_PREVENCION_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NOTIFICACION_PREVENCION_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_RESPUESTA_PREVENCION = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_RESPUESTA_PREVENCION = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_RESPUESTA_PREVENCION_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_RESPUESTA_PREVENCION_CONTENT_TYPE = "image/png";

    private static final Integer DEFAULT_TIPO_EVALUACION = 1;
    private static final Integer UPDATED_TIPO_EVALUACION = 2;

    private static final byte[] DEFAULT_ACTA_PROCEDE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ACTA_PROCEDE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_ACTA_PROCEDE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ACTA_PROCEDE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_ACUERDO_PROCEDE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ACUERDO_PROCEDE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_ACUERDO_PROCEDE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ACUERDO_PROCEDE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_NOTIFICACION_PROCEDE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_NOTIFICACION_PROCEDE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_NOTIFICACION_PROCEDE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NOTIFICACION_PROCEDE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_ACUERDO_NOPROCEDE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ACUERDO_NOPROCEDE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_ACUERDO_NOPROCEDE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ACUERDO_NOPROCEDE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_NOTIFICACION_NOPROCEDE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_NOTIFICACION_NOPROCEDE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_NOTIFICACION_NOPROCEDE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NOTIFICACION_NOPROCEDE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_ACUERDO_PRESENTACION = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ACUERDO_PRESENTACION = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_ACUERDO_PRESENTACION_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ACUERDO_PRESENTACION_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_NOTIFICACION_PRESENTACION = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_NOTIFICACION_PRESENTACION = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_NOTIFICACION_PRESENTACION_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_NOTIFICACION_PRESENTACION_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_STATUS_TRABAJO = false;
    private static final Boolean UPDATED_STATUS_TRABAJO = true;

    @Autowired
    private PeticionRepository peticionRepository;

    @Autowired
    private PeticionService peticionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPeticionMockMvc;

    private Peticion peticion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PeticionResource peticionResource = new PeticionResource(peticionService);
        this.restPeticionMockMvc = MockMvcBuilders.standaloneSetup(peticionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Peticion createEntity(EntityManager em) {
        Peticion peticion = new Peticion()
            .numero(DEFAULT_NUMERO)
            .nombre_solicitante(DEFAULT_NOMBRE_SOLICITANTE)
            .paterno_solicitante(DEFAULT_PATERNO_SOLICITANTE)
            .materno_solicitante(DEFAULT_MATERNO_SOLICITANTE)
            .cargo_solicitante(DEFAULT_CARGO_SOLICITANTE)
            .direccion_solicitante(DEFAULT_DIRECCION_SOLICITANTE)
            .acto_constar(DEFAULT_ACTO_CONSTAR)
            .fecha(DEFAULT_FECHA)
            .oficio(DEFAULT_OFICIO)
            .oficioContentType(DEFAULT_OFICIO_CONTENT_TYPE)
            .responsable(DEFAULT_RESPONSABLE)
            .status_prevencion(DEFAULT_STATUS_PREVENCION)
            .oficio_prevencion(DEFAULT_OFICIO_PREVENCION)
            .oficio_prevencionContentType(DEFAULT_OFICIO_PREVENCION_CONTENT_TYPE)
            .notificacion_prevencion(DEFAULT_NOTIFICACION_PREVENCION)
            .notificacion_prevencionContentType(DEFAULT_NOTIFICACION_PREVENCION_CONTENT_TYPE)
            .respuesta_prevencion(DEFAULT_RESPUESTA_PREVENCION)
            .respuesta_prevencionContentType(DEFAULT_RESPUESTA_PREVENCION_CONTENT_TYPE)
            .tipo_evaluacion(DEFAULT_TIPO_EVALUACION)
            .acta_procede(DEFAULT_ACTA_PROCEDE)
            .acta_procedeContentType(DEFAULT_ACTA_PROCEDE_CONTENT_TYPE)
            .acuerdo_procede(DEFAULT_ACUERDO_PROCEDE)
            .acuerdo_procedeContentType(DEFAULT_ACUERDO_PROCEDE_CONTENT_TYPE)
            .notificacion_procede(DEFAULT_NOTIFICACION_PROCEDE)
            .notificacion_procedeContentType(DEFAULT_NOTIFICACION_PROCEDE_CONTENT_TYPE)
            .acuerdo_noprocede(DEFAULT_ACUERDO_NOPROCEDE)
            .acuerdo_noprocedeContentType(DEFAULT_ACUERDO_NOPROCEDE_CONTENT_TYPE)
            .notificacion_noprocede(DEFAULT_NOTIFICACION_NOPROCEDE)
            .notificacion_noprocedeContentType(DEFAULT_NOTIFICACION_NOPROCEDE_CONTENT_TYPE)
            .acuerdo_presentacion(DEFAULT_ACUERDO_PRESENTACION)
            .acuerdo_presentacionContentType(DEFAULT_ACUERDO_PRESENTACION_CONTENT_TYPE)
            .notificacion_presentacion(DEFAULT_NOTIFICACION_PRESENTACION)
            .notificacion_presentacionContentType(DEFAULT_NOTIFICACION_PRESENTACION_CONTENT_TYPE)
            .status_trabajo(DEFAULT_STATUS_TRABAJO);
        // Add required entity
        Origen origen = OrigenResourceIntTest.createEntity(em);
        em.persist(origen);
        em.flush();
        peticion.setOrigen(origen);
        // Add required entity
        Peticionario peticionario = PeticionarioResourceIntTest.createEntity(em);
        em.persist(peticionario);
        em.flush();
        peticion.setPeticionario(peticionario);
        return peticion;
    }

    @Before
    public void initTest() {
        peticion = createEntity(em);
    }

    @Test
    @Transactional
    public void createPeticion() throws Exception {
        int databaseSizeBeforeCreate = peticionRepository.findAll().size();

        // Create the Peticion
        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isCreated());

        // Validate the Peticion in the database
        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeCreate + 1);
        Peticion testPeticion = peticionList.get(peticionList.size() - 1);
        assertThat(testPeticion.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testPeticion.getNombre_solicitante()).isEqualTo(DEFAULT_NOMBRE_SOLICITANTE);
        assertThat(testPeticion.getPaterno_solicitante()).isEqualTo(DEFAULT_PATERNO_SOLICITANTE);
        assertThat(testPeticion.getMaterno_solicitante()).isEqualTo(DEFAULT_MATERNO_SOLICITANTE);
        assertThat(testPeticion.getCargo_solicitante()).isEqualTo(DEFAULT_CARGO_SOLICITANTE);
        assertThat(testPeticion.getDireccion_solicitante()).isEqualTo(DEFAULT_DIRECCION_SOLICITANTE);
        assertThat(testPeticion.getActo_constar()).isEqualTo(DEFAULT_ACTO_CONSTAR);
        assertThat(testPeticion.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testPeticion.getOficio()).isEqualTo(DEFAULT_OFICIO);
        assertThat(testPeticion.getOficioContentType()).isEqualTo(DEFAULT_OFICIO_CONTENT_TYPE);
        assertThat(testPeticion.getResponsable()).isEqualTo(DEFAULT_RESPONSABLE);
        assertThat(testPeticion.isStatus_prevencion()).isEqualTo(DEFAULT_STATUS_PREVENCION);
        assertThat(testPeticion.getOficio_prevencion()).isEqualTo(DEFAULT_OFICIO_PREVENCION);
        assertThat(testPeticion.getOficio_prevencionContentType()).isEqualTo(DEFAULT_OFICIO_PREVENCION_CONTENT_TYPE);
        assertThat(testPeticion.getNotificacion_prevencion()).isEqualTo(DEFAULT_NOTIFICACION_PREVENCION);
        assertThat(testPeticion.getNotificacion_prevencionContentType()).isEqualTo(DEFAULT_NOTIFICACION_PREVENCION_CONTENT_TYPE);
        assertThat(testPeticion.getRespuesta_prevencion()).isEqualTo(DEFAULT_RESPUESTA_PREVENCION);
        assertThat(testPeticion.getRespuesta_prevencionContentType()).isEqualTo(DEFAULT_RESPUESTA_PREVENCION_CONTENT_TYPE);
        assertThat(testPeticion.getTipo_evaluacion()).isEqualTo(DEFAULT_TIPO_EVALUACION);
        assertThat(testPeticion.getActa_procede()).isEqualTo(DEFAULT_ACTA_PROCEDE);
        assertThat(testPeticion.getActa_procedeContentType()).isEqualTo(DEFAULT_ACTA_PROCEDE_CONTENT_TYPE);
        assertThat(testPeticion.getAcuerdo_procede()).isEqualTo(DEFAULT_ACUERDO_PROCEDE);
        assertThat(testPeticion.getAcuerdo_procedeContentType()).isEqualTo(DEFAULT_ACUERDO_PROCEDE_CONTENT_TYPE);
        assertThat(testPeticion.getNotificacion_procede()).isEqualTo(DEFAULT_NOTIFICACION_PROCEDE);
        assertThat(testPeticion.getNotificacion_procedeContentType()).isEqualTo(DEFAULT_NOTIFICACION_PROCEDE_CONTENT_TYPE);
        assertThat(testPeticion.getAcuerdo_noprocede()).isEqualTo(DEFAULT_ACUERDO_NOPROCEDE);
        assertThat(testPeticion.getAcuerdo_noprocedeContentType()).isEqualTo(DEFAULT_ACUERDO_NOPROCEDE_CONTENT_TYPE);
        assertThat(testPeticion.getNotificacion_noprocede()).isEqualTo(DEFAULT_NOTIFICACION_NOPROCEDE);
        assertThat(testPeticion.getNotificacion_noprocedeContentType()).isEqualTo(DEFAULT_NOTIFICACION_NOPROCEDE_CONTENT_TYPE);
        assertThat(testPeticion.getAcuerdo_presentacion()).isEqualTo(DEFAULT_ACUERDO_PRESENTACION);
        assertThat(testPeticion.getAcuerdo_presentacionContentType()).isEqualTo(DEFAULT_ACUERDO_PRESENTACION_CONTENT_TYPE);
        assertThat(testPeticion.getNotificacion_presentacion()).isEqualTo(DEFAULT_NOTIFICACION_PRESENTACION);
        assertThat(testPeticion.getNotificacion_presentacionContentType()).isEqualTo(DEFAULT_NOTIFICACION_PRESENTACION_CONTENT_TYPE);
        assertThat(testPeticion.isStatus_trabajo()).isEqualTo(DEFAULT_STATUS_TRABAJO);
    }

    @Test
    @Transactional
    public void createPeticionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = peticionRepository.findAll().size();

        // Create the Peticion with an existing ID
        peticion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setNumero(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNombre_solicitanteIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setNombre_solicitante(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaterno_solicitanteIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setPaterno_solicitante(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaterno_solicitanteIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setMaterno_solicitante(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCargo_solicitanteIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setCargo_solicitante(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDireccion_solicitanteIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setDireccion_solicitante(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActo_constarIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setActo_constar(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setFecha(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOficioIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setOficio(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResponsableIsRequired() throws Exception {
        int databaseSizeBeforeTest = peticionRepository.findAll().size();
        // set the field null
        peticion.setResponsable(null);

        // Create the Peticion, which fails.

        restPeticionMockMvc.perform(post("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isBadRequest());

        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPeticions() throws Exception {
        // Initialize the database
        peticionRepository.saveAndFlush(peticion);

        // Get all the peticionList
        restPeticionMockMvc.perform(get("/api/peticions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(peticion.getId().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.toString())))
            .andExpect(jsonPath("$.[*].nombre_solicitante").value(hasItem(DEFAULT_NOMBRE_SOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].paterno_solicitante").value(hasItem(DEFAULT_PATERNO_SOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].materno_solicitante").value(hasItem(DEFAULT_MATERNO_SOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].cargo_solicitante").value(hasItem(DEFAULT_CARGO_SOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].direccion_solicitante").value(hasItem(DEFAULT_DIRECCION_SOLICITANTE.toString())))
            .andExpect(jsonPath("$.[*].acto_constar").value(hasItem(DEFAULT_ACTO_CONSTAR.toString())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(sameInstant(DEFAULT_FECHA))))
            .andExpect(jsonPath("$.[*].oficioContentType").value(hasItem(DEFAULT_OFICIO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].oficio").value(hasItem(Base64Utils.encodeToString(DEFAULT_OFICIO))))
            .andExpect(jsonPath("$.[*].responsable").value(hasItem(DEFAULT_RESPONSABLE.toString())))
            .andExpect(jsonPath("$.[*].status_prevencion").value(hasItem(DEFAULT_STATUS_PREVENCION.booleanValue())))
            .andExpect(jsonPath("$.[*].oficio_prevencionContentType").value(hasItem(DEFAULT_OFICIO_PREVENCION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].oficio_prevencion").value(hasItem(Base64Utils.encodeToString(DEFAULT_OFICIO_PREVENCION))))
            .andExpect(jsonPath("$.[*].notificacion_prevencionContentType").value(hasItem(DEFAULT_NOTIFICACION_PREVENCION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].notificacion_prevencion").value(hasItem(Base64Utils.encodeToString(DEFAULT_NOTIFICACION_PREVENCION))))
            .andExpect(jsonPath("$.[*].respuesta_prevencionContentType").value(hasItem(DEFAULT_RESPUESTA_PREVENCION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].respuesta_prevencion").value(hasItem(Base64Utils.encodeToString(DEFAULT_RESPUESTA_PREVENCION))))
            .andExpect(jsonPath("$.[*].tipo_evaluacion").value(hasItem(DEFAULT_TIPO_EVALUACION)))
            .andExpect(jsonPath("$.[*].acta_procedeContentType").value(hasItem(DEFAULT_ACTA_PROCEDE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].acta_procede").value(hasItem(Base64Utils.encodeToString(DEFAULT_ACTA_PROCEDE))))
            .andExpect(jsonPath("$.[*].acuerdo_procedeContentType").value(hasItem(DEFAULT_ACUERDO_PROCEDE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].acuerdo_procede").value(hasItem(Base64Utils.encodeToString(DEFAULT_ACUERDO_PROCEDE))))
            .andExpect(jsonPath("$.[*].notificacion_procedeContentType").value(hasItem(DEFAULT_NOTIFICACION_PROCEDE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].notificacion_procede").value(hasItem(Base64Utils.encodeToString(DEFAULT_NOTIFICACION_PROCEDE))))
            .andExpect(jsonPath("$.[*].acuerdo_noprocedeContentType").value(hasItem(DEFAULT_ACUERDO_NOPROCEDE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].acuerdo_noprocede").value(hasItem(Base64Utils.encodeToString(DEFAULT_ACUERDO_NOPROCEDE))))
            .andExpect(jsonPath("$.[*].notificacion_noprocedeContentType").value(hasItem(DEFAULT_NOTIFICACION_NOPROCEDE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].notificacion_noprocede").value(hasItem(Base64Utils.encodeToString(DEFAULT_NOTIFICACION_NOPROCEDE))))
            .andExpect(jsonPath("$.[*].acuerdo_presentacionContentType").value(hasItem(DEFAULT_ACUERDO_PRESENTACION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].acuerdo_presentacion").value(hasItem(Base64Utils.encodeToString(DEFAULT_ACUERDO_PRESENTACION))))
            .andExpect(jsonPath("$.[*].notificacion_presentacionContentType").value(hasItem(DEFAULT_NOTIFICACION_PRESENTACION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].notificacion_presentacion").value(hasItem(Base64Utils.encodeToString(DEFAULT_NOTIFICACION_PRESENTACION))))
            .andExpect(jsonPath("$.[*].status_trabajo").value(hasItem(DEFAULT_STATUS_TRABAJO.booleanValue())));
    }

    @Test
    @Transactional
    public void getPeticion() throws Exception {
        // Initialize the database
        peticionRepository.saveAndFlush(peticion);

        // Get the peticion
        restPeticionMockMvc.perform(get("/api/peticions/{id}", peticion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(peticion.getId().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.toString()))
            .andExpect(jsonPath("$.nombre_solicitante").value(DEFAULT_NOMBRE_SOLICITANTE.toString()))
            .andExpect(jsonPath("$.paterno_solicitante").value(DEFAULT_PATERNO_SOLICITANTE.toString()))
            .andExpect(jsonPath("$.materno_solicitante").value(DEFAULT_MATERNO_SOLICITANTE.toString()))
            .andExpect(jsonPath("$.cargo_solicitante").value(DEFAULT_CARGO_SOLICITANTE.toString()))
            .andExpect(jsonPath("$.direccion_solicitante").value(DEFAULT_DIRECCION_SOLICITANTE.toString()))
            .andExpect(jsonPath("$.acto_constar").value(DEFAULT_ACTO_CONSTAR.toString()))
            .andExpect(jsonPath("$.fecha").value(sameInstant(DEFAULT_FECHA)))
            .andExpect(jsonPath("$.oficioContentType").value(DEFAULT_OFICIO_CONTENT_TYPE))
            .andExpect(jsonPath("$.oficio").value(Base64Utils.encodeToString(DEFAULT_OFICIO)))
            .andExpect(jsonPath("$.responsable").value(DEFAULT_RESPONSABLE.toString()))
            .andExpect(jsonPath("$.status_prevencion").value(DEFAULT_STATUS_PREVENCION.booleanValue()))
            .andExpect(jsonPath("$.oficio_prevencionContentType").value(DEFAULT_OFICIO_PREVENCION_CONTENT_TYPE))
            .andExpect(jsonPath("$.oficio_prevencion").value(Base64Utils.encodeToString(DEFAULT_OFICIO_PREVENCION)))
            .andExpect(jsonPath("$.notificacion_prevencionContentType").value(DEFAULT_NOTIFICACION_PREVENCION_CONTENT_TYPE))
            .andExpect(jsonPath("$.notificacion_prevencion").value(Base64Utils.encodeToString(DEFAULT_NOTIFICACION_PREVENCION)))
            .andExpect(jsonPath("$.respuesta_prevencionContentType").value(DEFAULT_RESPUESTA_PREVENCION_CONTENT_TYPE))
            .andExpect(jsonPath("$.respuesta_prevencion").value(Base64Utils.encodeToString(DEFAULT_RESPUESTA_PREVENCION)))
            .andExpect(jsonPath("$.tipo_evaluacion").value(DEFAULT_TIPO_EVALUACION))
            .andExpect(jsonPath("$.acta_procedeContentType").value(DEFAULT_ACTA_PROCEDE_CONTENT_TYPE))
            .andExpect(jsonPath("$.acta_procede").value(Base64Utils.encodeToString(DEFAULT_ACTA_PROCEDE)))
            .andExpect(jsonPath("$.acuerdo_procedeContentType").value(DEFAULT_ACUERDO_PROCEDE_CONTENT_TYPE))
            .andExpect(jsonPath("$.acuerdo_procede").value(Base64Utils.encodeToString(DEFAULT_ACUERDO_PROCEDE)))
            .andExpect(jsonPath("$.notificacion_procedeContentType").value(DEFAULT_NOTIFICACION_PROCEDE_CONTENT_TYPE))
            .andExpect(jsonPath("$.notificacion_procede").value(Base64Utils.encodeToString(DEFAULT_NOTIFICACION_PROCEDE)))
            .andExpect(jsonPath("$.acuerdo_noprocedeContentType").value(DEFAULT_ACUERDO_NOPROCEDE_CONTENT_TYPE))
            .andExpect(jsonPath("$.acuerdo_noprocede").value(Base64Utils.encodeToString(DEFAULT_ACUERDO_NOPROCEDE)))
            .andExpect(jsonPath("$.notificacion_noprocedeContentType").value(DEFAULT_NOTIFICACION_NOPROCEDE_CONTENT_TYPE))
            .andExpect(jsonPath("$.notificacion_noprocede").value(Base64Utils.encodeToString(DEFAULT_NOTIFICACION_NOPROCEDE)))
            .andExpect(jsonPath("$.acuerdo_presentacionContentType").value(DEFAULT_ACUERDO_PRESENTACION_CONTENT_TYPE))
            .andExpect(jsonPath("$.acuerdo_presentacion").value(Base64Utils.encodeToString(DEFAULT_ACUERDO_PRESENTACION)))
            .andExpect(jsonPath("$.notificacion_presentacionContentType").value(DEFAULT_NOTIFICACION_PRESENTACION_CONTENT_TYPE))
            .andExpect(jsonPath("$.notificacion_presentacion").value(Base64Utils.encodeToString(DEFAULT_NOTIFICACION_PRESENTACION)))
            .andExpect(jsonPath("$.status_trabajo").value(DEFAULT_STATUS_TRABAJO.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPeticion() throws Exception {
        // Get the peticion
        restPeticionMockMvc.perform(get("/api/peticions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePeticion() throws Exception {
        // Initialize the database
        peticionService.save(peticion);

        int databaseSizeBeforeUpdate = peticionRepository.findAll().size();

        // Update the peticion
        Peticion updatedPeticion = peticionRepository.findOne(peticion.getId());
        updatedPeticion
            .numero(UPDATED_NUMERO)
            .nombre_solicitante(UPDATED_NOMBRE_SOLICITANTE)
            .paterno_solicitante(UPDATED_PATERNO_SOLICITANTE)
            .materno_solicitante(UPDATED_MATERNO_SOLICITANTE)
            .cargo_solicitante(UPDATED_CARGO_SOLICITANTE)
            .direccion_solicitante(UPDATED_DIRECCION_SOLICITANTE)
            .acto_constar(UPDATED_ACTO_CONSTAR)
            .fecha(UPDATED_FECHA)
            .oficio(UPDATED_OFICIO)
            .oficioContentType(UPDATED_OFICIO_CONTENT_TYPE)
            .responsable(UPDATED_RESPONSABLE)
            .status_prevencion(UPDATED_STATUS_PREVENCION)
            .oficio_prevencion(UPDATED_OFICIO_PREVENCION)
            .oficio_prevencionContentType(UPDATED_OFICIO_PREVENCION_CONTENT_TYPE)
            .notificacion_prevencion(UPDATED_NOTIFICACION_PREVENCION)
            .notificacion_prevencionContentType(UPDATED_NOTIFICACION_PREVENCION_CONTENT_TYPE)
            .respuesta_prevencion(UPDATED_RESPUESTA_PREVENCION)
            .respuesta_prevencionContentType(UPDATED_RESPUESTA_PREVENCION_CONTENT_TYPE)
            .tipo_evaluacion(UPDATED_TIPO_EVALUACION)
            .acta_procede(UPDATED_ACTA_PROCEDE)
            .acta_procedeContentType(UPDATED_ACTA_PROCEDE_CONTENT_TYPE)
            .acuerdo_procede(UPDATED_ACUERDO_PROCEDE)
            .acuerdo_procedeContentType(UPDATED_ACUERDO_PROCEDE_CONTENT_TYPE)
            .notificacion_procede(UPDATED_NOTIFICACION_PROCEDE)
            .notificacion_procedeContentType(UPDATED_NOTIFICACION_PROCEDE_CONTENT_TYPE)
            .acuerdo_noprocede(UPDATED_ACUERDO_NOPROCEDE)
            .acuerdo_noprocedeContentType(UPDATED_ACUERDO_NOPROCEDE_CONTENT_TYPE)
            .notificacion_noprocede(UPDATED_NOTIFICACION_NOPROCEDE)
            .notificacion_noprocedeContentType(UPDATED_NOTIFICACION_NOPROCEDE_CONTENT_TYPE)
            .acuerdo_presentacion(UPDATED_ACUERDO_PRESENTACION)
            .acuerdo_presentacionContentType(UPDATED_ACUERDO_PRESENTACION_CONTENT_TYPE)
            .notificacion_presentacion(UPDATED_NOTIFICACION_PRESENTACION)
            .notificacion_presentacionContentType(UPDATED_NOTIFICACION_PRESENTACION_CONTENT_TYPE)
            .status_trabajo(UPDATED_STATUS_TRABAJO);

        restPeticionMockMvc.perform(put("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPeticion)))
            .andExpect(status().isOk());

        // Validate the Peticion in the database
        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeUpdate);
        Peticion testPeticion = peticionList.get(peticionList.size() - 1);
        assertThat(testPeticion.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testPeticion.getNombre_solicitante()).isEqualTo(UPDATED_NOMBRE_SOLICITANTE);
        assertThat(testPeticion.getPaterno_solicitante()).isEqualTo(UPDATED_PATERNO_SOLICITANTE);
        assertThat(testPeticion.getMaterno_solicitante()).isEqualTo(UPDATED_MATERNO_SOLICITANTE);
        assertThat(testPeticion.getCargo_solicitante()).isEqualTo(UPDATED_CARGO_SOLICITANTE);
        assertThat(testPeticion.getDireccion_solicitante()).isEqualTo(UPDATED_DIRECCION_SOLICITANTE);
        assertThat(testPeticion.getActo_constar()).isEqualTo(UPDATED_ACTO_CONSTAR);
        assertThat(testPeticion.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testPeticion.getOficio()).isEqualTo(UPDATED_OFICIO);
        assertThat(testPeticion.getOficioContentType()).isEqualTo(UPDATED_OFICIO_CONTENT_TYPE);
        assertThat(testPeticion.getResponsable()).isEqualTo(UPDATED_RESPONSABLE);
        assertThat(testPeticion.isStatus_prevencion()).isEqualTo(UPDATED_STATUS_PREVENCION);
        assertThat(testPeticion.getOficio_prevencion()).isEqualTo(UPDATED_OFICIO_PREVENCION);
        assertThat(testPeticion.getOficio_prevencionContentType()).isEqualTo(UPDATED_OFICIO_PREVENCION_CONTENT_TYPE);
        assertThat(testPeticion.getNotificacion_prevencion()).isEqualTo(UPDATED_NOTIFICACION_PREVENCION);
        assertThat(testPeticion.getNotificacion_prevencionContentType()).isEqualTo(UPDATED_NOTIFICACION_PREVENCION_CONTENT_TYPE);
        assertThat(testPeticion.getRespuesta_prevencion()).isEqualTo(UPDATED_RESPUESTA_PREVENCION);
        assertThat(testPeticion.getRespuesta_prevencionContentType()).isEqualTo(UPDATED_RESPUESTA_PREVENCION_CONTENT_TYPE);
        assertThat(testPeticion.getTipo_evaluacion()).isEqualTo(UPDATED_TIPO_EVALUACION);
        assertThat(testPeticion.getActa_procede()).isEqualTo(UPDATED_ACTA_PROCEDE);
        assertThat(testPeticion.getActa_procedeContentType()).isEqualTo(UPDATED_ACTA_PROCEDE_CONTENT_TYPE);
        assertThat(testPeticion.getAcuerdo_procede()).isEqualTo(UPDATED_ACUERDO_PROCEDE);
        assertThat(testPeticion.getAcuerdo_procedeContentType()).isEqualTo(UPDATED_ACUERDO_PROCEDE_CONTENT_TYPE);
        assertThat(testPeticion.getNotificacion_procede()).isEqualTo(UPDATED_NOTIFICACION_PROCEDE);
        assertThat(testPeticion.getNotificacion_procedeContentType()).isEqualTo(UPDATED_NOTIFICACION_PROCEDE_CONTENT_TYPE);
        assertThat(testPeticion.getAcuerdo_noprocede()).isEqualTo(UPDATED_ACUERDO_NOPROCEDE);
        assertThat(testPeticion.getAcuerdo_noprocedeContentType()).isEqualTo(UPDATED_ACUERDO_NOPROCEDE_CONTENT_TYPE);
        assertThat(testPeticion.getNotificacion_noprocede()).isEqualTo(UPDATED_NOTIFICACION_NOPROCEDE);
        assertThat(testPeticion.getNotificacion_noprocedeContentType()).isEqualTo(UPDATED_NOTIFICACION_NOPROCEDE_CONTENT_TYPE);
        assertThat(testPeticion.getAcuerdo_presentacion()).isEqualTo(UPDATED_ACUERDO_PRESENTACION);
        assertThat(testPeticion.getAcuerdo_presentacionContentType()).isEqualTo(UPDATED_ACUERDO_PRESENTACION_CONTENT_TYPE);
        assertThat(testPeticion.getNotificacion_presentacion()).isEqualTo(UPDATED_NOTIFICACION_PRESENTACION);
        assertThat(testPeticion.getNotificacion_presentacionContentType()).isEqualTo(UPDATED_NOTIFICACION_PRESENTACION_CONTENT_TYPE);
        assertThat(testPeticion.isStatus_trabajo()).isEqualTo(UPDATED_STATUS_TRABAJO);
    }

    @Test
    @Transactional
    public void updateNonExistingPeticion() throws Exception {
        int databaseSizeBeforeUpdate = peticionRepository.findAll().size();

        // Create the Peticion

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPeticionMockMvc.perform(put("/api/peticions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(peticion)))
            .andExpect(status().isCreated());

        // Validate the Peticion in the database
        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePeticion() throws Exception {
        // Initialize the database
        peticionService.save(peticion);

        int databaseSizeBeforeDelete = peticionRepository.findAll().size();

        // Get the peticion
        restPeticionMockMvc.perform(delete("/api/peticions/{id}", peticion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Peticion> peticionList = peticionRepository.findAll();
        assertThat(peticionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Peticion.class);
    }
}
