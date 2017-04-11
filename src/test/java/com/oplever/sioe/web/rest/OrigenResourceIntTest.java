package com.oplever.sioe.web.rest;

import com.oplever.sioe.SioeSqlApp;

import com.oplever.sioe.domain.Origen;
import com.oplever.sioe.repository.OrigenRepository;
import com.oplever.sioe.service.OrigenService;
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

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the OrigenResource REST controller.
 *
 * @see OrigenResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SioeSqlApp.class)
public class OrigenResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRIVILEGIO = 1;
    private static final Integer UPDATED_PRIVILEGIO = 2;

    private static final String DEFAULT_ZONA = "AAAAAAAAAA";
    private static final String UPDATED_ZONA = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRITO = "AAAAAAAAAA";
    private static final String UPDATED_DISTRITO = "BBBBBBBBBB";

    private static final String DEFAULT_MUNICIPIO = "AAAAAAAAAA";
    private static final String UPDATED_MUNICIPIO = "BBBBBBBBBB";

    @Autowired
    private OrigenRepository origenRepository;

    @Autowired
    private OrigenService origenService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrigenMockMvc;

    private Origen origen;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OrigenResource origenResource = new OrigenResource(origenService);
        this.restOrigenMockMvc = MockMvcBuilders.standaloneSetup(origenResource)
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
    public static Origen createEntity(EntityManager em) {
        Origen origen = new Origen()
            .nombre(DEFAULT_NOMBRE)
            .privilegio(DEFAULT_PRIVILEGIO)
            .zona(DEFAULT_ZONA)
            .distrito(DEFAULT_DISTRITO)
            .municipio(DEFAULT_MUNICIPIO);
        return origen;
    }

    @Before
    public void initTest() {
        origen = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrigen() throws Exception {
        int databaseSizeBeforeCreate = origenRepository.findAll().size();

        // Create the Origen
        restOrigenMockMvc.perform(post("/api/origens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origen)))
            .andExpect(status().isCreated());

        // Validate the Origen in the database
        List<Origen> origenList = origenRepository.findAll();
        assertThat(origenList).hasSize(databaseSizeBeforeCreate + 1);
        Origen testOrigen = origenList.get(origenList.size() - 1);
        assertThat(testOrigen.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testOrigen.getPrivilegio()).isEqualTo(DEFAULT_PRIVILEGIO);
        assertThat(testOrigen.getZona()).isEqualTo(DEFAULT_ZONA);
        assertThat(testOrigen.getDistrito()).isEqualTo(DEFAULT_DISTRITO);
        assertThat(testOrigen.getMunicipio()).isEqualTo(DEFAULT_MUNICIPIO);
    }

    @Test
    @Transactional
    public void createOrigenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = origenRepository.findAll().size();

        // Create the Origen with an existing ID
        origen.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrigenMockMvc.perform(post("/api/origens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origen)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Origen> origenList = origenRepository.findAll();
        assertThat(origenList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrigens() throws Exception {
        // Initialize the database
        origenRepository.saveAndFlush(origen);

        // Get all the origenList
        restOrigenMockMvc.perform(get("/api/origens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(origen.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].privilegio").value(hasItem(DEFAULT_PRIVILEGIO)))
            .andExpect(jsonPath("$.[*].zona").value(hasItem(DEFAULT_ZONA.toString())))
            .andExpect(jsonPath("$.[*].distrito").value(hasItem(DEFAULT_DISTRITO.toString())))
            .andExpect(jsonPath("$.[*].municipio").value(hasItem(DEFAULT_MUNICIPIO.toString())));
    }

    @Test
    @Transactional
    public void getOrigen() throws Exception {
        // Initialize the database
        origenRepository.saveAndFlush(origen);

        // Get the origen
        restOrigenMockMvc.perform(get("/api/origens/{id}", origen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(origen.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.privilegio").value(DEFAULT_PRIVILEGIO))
            .andExpect(jsonPath("$.zona").value(DEFAULT_ZONA.toString()))
            .andExpect(jsonPath("$.distrito").value(DEFAULT_DISTRITO.toString()))
            .andExpect(jsonPath("$.municipio").value(DEFAULT_MUNICIPIO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOrigen() throws Exception {
        // Get the origen
        restOrigenMockMvc.perform(get("/api/origens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrigen() throws Exception {
        // Initialize the database
        origenService.save(origen);

        int databaseSizeBeforeUpdate = origenRepository.findAll().size();

        // Update the origen
        Origen updatedOrigen = origenRepository.findOne(origen.getId());
        updatedOrigen
            .nombre(UPDATED_NOMBRE)
            .privilegio(UPDATED_PRIVILEGIO)
            .zona(UPDATED_ZONA)
            .distrito(UPDATED_DISTRITO)
            .municipio(UPDATED_MUNICIPIO);

        restOrigenMockMvc.perform(put("/api/origens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrigen)))
            .andExpect(status().isOk());

        // Validate the Origen in the database
        List<Origen> origenList = origenRepository.findAll();
        assertThat(origenList).hasSize(databaseSizeBeforeUpdate);
        Origen testOrigen = origenList.get(origenList.size() - 1);
        assertThat(testOrigen.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testOrigen.getPrivilegio()).isEqualTo(UPDATED_PRIVILEGIO);
        assertThat(testOrigen.getZona()).isEqualTo(UPDATED_ZONA);
        assertThat(testOrigen.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testOrigen.getMunicipio()).isEqualTo(UPDATED_MUNICIPIO);
    }

    @Test
    @Transactional
    public void updateNonExistingOrigen() throws Exception {
        int databaseSizeBeforeUpdate = origenRepository.findAll().size();

        // Create the Origen

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOrigenMockMvc.perform(put("/api/origens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(origen)))
            .andExpect(status().isCreated());

        // Validate the Origen in the database
        List<Origen> origenList = origenRepository.findAll();
        assertThat(origenList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOrigen() throws Exception {
        // Initialize the database
        origenService.save(origen);

        int databaseSizeBeforeDelete = origenRepository.findAll().size();

        // Get the origen
        restOrigenMockMvc.perform(delete("/api/origens/{id}", origen.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Origen> origenList = origenRepository.findAll();
        assertThat(origenList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Origen.class);
    }
}
