package com.oplever.sioe.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oplever.sioe.domain.Peticionario;
import com.oplever.sioe.service.PeticionarioService;
import com.oplever.sioe.web.rest.util.HeaderUtil;
import com.oplever.sioe.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Peticionario.
 */
@RestController
@RequestMapping("/api")
public class PeticionarioResource {

    private final Logger log = LoggerFactory.getLogger(PeticionarioResource.class);

    private static final String ENTITY_NAME = "peticionario";
        
    private final PeticionarioService peticionarioService;

    public PeticionarioResource(PeticionarioService peticionarioService) {
        this.peticionarioService = peticionarioService;
    }

    /**
     * POST  /peticionarios : Create a new peticionario.
     *
     * @param peticionario the peticionario to create
     * @return the ResponseEntity with status 201 (Created) and with body the new peticionario, or with status 400 (Bad Request) if the peticionario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/peticionarios")
    @Timed
    public ResponseEntity<Peticionario> createPeticionario(@RequestBody Peticionario peticionario) throws URISyntaxException {
        log.debug("REST request to save Peticionario : {}", peticionario);
        if (peticionario.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new peticionario cannot already have an ID")).body(null);
        }
        Peticionario result = peticionarioService.save(peticionario);
        return ResponseEntity.created(new URI("/api/peticionarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /peticionarios : Updates an existing peticionario.
     *
     * @param peticionario the peticionario to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated peticionario,
     * or with status 400 (Bad Request) if the peticionario is not valid,
     * or with status 500 (Internal Server Error) if the peticionario couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/peticionarios")
    @Timed
    public ResponseEntity<Peticionario> updatePeticionario(@RequestBody Peticionario peticionario) throws URISyntaxException {
        log.debug("REST request to update Peticionario : {}", peticionario);
        if (peticionario.getId() == null) {
            return createPeticionario(peticionario);
        }
        Peticionario result = peticionarioService.save(peticionario);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, peticionario.getId().toString()))
            .body(result);
    }

    /**
     * GET  /peticionarios : get all the peticionarios.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of peticionarios in body
     */
    @GetMapping("/peticionarios")
    @Timed
    public ResponseEntity<List<Peticionario>> getAllPeticionarios(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Peticionarios");
        Page<Peticionario> page = peticionarioService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/peticionarios");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /peticionarios/:id : get the "id" peticionario.
     *
     * @param id the id of the peticionario to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the peticionario, or with status 404 (Not Found)
     */
    @GetMapping("/peticionarios/{id}")
    @Timed
    public ResponseEntity<Peticionario> getPeticionario(@PathVariable Long id) {
        log.debug("REST request to get Peticionario : {}", id);
        Peticionario peticionario = peticionarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(peticionario));
    }

    /**
     * DELETE  /peticionarios/:id : delete the "id" peticionario.
     *
     * @param id the id of the peticionario to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/peticionarios/{id}")
    @Timed
    public ResponseEntity<Void> deletePeticionario(@PathVariable Long id) {
        log.debug("REST request to delete Peticionario : {}", id);
        peticionarioService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
