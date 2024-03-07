package co.vinni.soapproyectobase.controladores;


import co.vinni.soapproyectobase.dto.EquipoDto;
import co.vinni.soapproyectobase.entidades.Equipo;
import co.vinni.soapproyectobase.servicios.ServicioEquipos;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/api/equipos")
@CrossOrigin(origins = "${spring.webmvc.cors.allowed-origins}",
        methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})

public class ControladorEquiposANG {
    private static final Logger logger = LogManager.getLogger(ControladorEquipos.class);

    @Autowired
    ServicioEquipos servicioEquipos;

    @GetMapping("/")
    public ResponseEntity<List<EquipoDto>> listarEquipos(){

        return ResponseEntity.ok(servicioEquipos.obtenerEquipos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipoDto> obtenerPorId(@PathVariable("serial") Long serial) {
        return ResponseEntity.ok(servicioEquipos.obtenerEquipo(serial));
    }


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EquipoDto> crear(@RequestBody EquipoDto entityDto) {
        entityDto = servicioEquipos.registrar(entityDto);

        return new ResponseEntity<>(entityDto, HttpStatus.CREATED);
    }

}