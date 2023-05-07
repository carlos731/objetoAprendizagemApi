package com.senac.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.senac.api.entity.GrauDificuldade;
import com.senac.api.entity.ObjetoAprendizagem;
import com.senac.api.entity.SituacaoAprendizagem;
import com.senac.api.entity.Usuario;
import com.senac.api.request.ObjetoAprendizagemRequest;
import com.senac.api.response.ObjetoAprendizagemResponse;
import com.senac.api.service.ObjetoAprendizagemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/objetoAprendizagem")
public class ObjetoAprendizagemController {

	@Autowired
	private ObjetoAprendizagemService objetoAprendizagemService;
	
	@PostMapping
	public ResponseEntity<ObjetoAprendizagemResponse> adicionar(@Valid @RequestBody ObjetoAprendizagemRequest objReq){
		ModelMapper mapper = new ModelMapper();
		ObjetoAprendizagem obj = mapper.map(objReq, ObjetoAprendizagem.class);
		obj = objetoAprendizagemService.adicionar(obj);
		return new ResponseEntity<ObjetoAprendizagemResponse>(mapper.map(obj, ObjetoAprendizagemResponse.class), HttpStatus.CREATED);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<ObjetoAprendizagemResponse> upload(
				@RequestParam("descricao") String descricao,
				@RequestParam("file") MultipartFile file,
				@RequestParam("grauDificuldadeId") GrauDificuldade grauDificuldadeId,
				@RequestParam("usuarioId") Usuario usuarioId,
				@RequestParam("aprendizagem") List<SituacaoAprendizagem> aprendizagens
			) throws Exception{
		
		ModelMapper mapper = new ModelMapper();
		ObjetoAprendizagem obj = new ObjetoAprendizagem();
		obj.setDescricao(file.getOriginalFilename());
		obj.setBlob(file.getBytes());
		obj.setTipo(file.getContentType());
		obj.setStatus(true);
		obj.setGrauDificuldadeId(grauDificuldadeId);
		obj.setUsuarioId(usuarioId);
		obj.setAprendizagens(aprendizagens);
		
		obj = objetoAprendizagemService.upload(file, obj);
		
		//String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/objetoAprendizagem/download/").path(obj.getId().toString()).toUriString();
		
		return new ResponseEntity<ObjetoAprendizagemResponse>(mapper.map(obj, ObjetoAprendizagemResponse.class), HttpStatus.CREATED);
	}
	
	@GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) throws Exception {
        Optional<ObjetoAprendizagem> arquivo = null;
        arquivo = objetoAprendizagemService.obterPorId(fileId);
        System.out.println(arquivo.get().getTipo());
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(arquivo.get().getTipo()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "arquivo; filename=\"" + arquivo.get().getDescricao()
                + "\"")
                .body(new ByteArrayResource(arquivo.get().getBlob()));
    }

	@GetMapping
	public ResponseEntity<List<ObjetoAprendizagemResponse>> obterTodos(){
		List<ObjetoAprendizagem> objs = objetoAprendizagemService.obterTodos();
		ModelMapper mapper = new ModelMapper();
		List<ObjetoAprendizagemResponse> objRes = objs.stream().map(
				obj -> mapper.map(obj, ObjetoAprendizagemResponse.class)).collect(Collectors.toList());
		return new ResponseEntity<List<ObjetoAprendizagemResponse>>(objRes, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<ObjetoAprendizagemResponse>> obterPorId(@PathVariable Long id){
		Optional<ObjetoAprendizagem> obj = objetoAprendizagemService.obterPorId(id);
		ObjetoAprendizagemResponse objRes = new ModelMapper().map(obj.get(), ObjetoAprendizagemResponse.class);
		return new ResponseEntity<Optional<ObjetoAprendizagemResponse>>(Optional.of(objRes), HttpStatus.OK);
	}
	
	@GetMapping("/situacaoAprendizagem/{id}")
	public ResponseEntity<List<ObjetoAprendizagemResponse>> obterPorSituacaoAprendizagem(@PathVariable Long id){
		List<ObjetoAprendizagem> objetos = objetoAprendizagemService.obterPorSituacaoAprendizagem(id);
		ModelMapper mapper = new ModelMapper();
		List<ObjetoAprendizagemResponse> resposta = objetos.stream().map(objeto -> mapper.map(objeto, ObjetoAprendizagemResponse.class)).collect(Collectors.toList());
		return new ResponseEntity<>(resposta, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ObjetoAprendizagemResponse> atualizar(@PathVariable Long id, ObjetoAprendizagemRequest objReq){
		ModelMapper mapper = new ModelMapper();
		ObjetoAprendizagem obj = mapper.map(objReq, ObjetoAprendizagem.class);
		obj = objetoAprendizagemService.atualizar(id, obj);
		return new ResponseEntity<ObjetoAprendizagemResponse>(mapper.map(obj, ObjetoAprendizagemResponse.class), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		objetoAprendizagemService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
}
