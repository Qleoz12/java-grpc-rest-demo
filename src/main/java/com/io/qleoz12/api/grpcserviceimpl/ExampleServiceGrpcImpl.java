package com.io.qleoz12.api.grpcserviceimpl;

import com.google.protobuf.StringValue;
import com.io.qleoz12.api.entities.Schools;
import com.io.qleoz12.api.services.SchoolsService;
import io.grpc.stub.StreamObserver;
import io.qleoz12.*;

import io.qleoz12.ExampleDto;
import net.devh.boot.grpc.server.service.GrpcService;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@GrpcService
public class ExampleServiceGrpcImpl extends ExampleServiceGrpc.ExampleServiceImplBase {

    private final SchoolsService service;
    private final ModelMapper modelMapper;

    public ExampleServiceGrpcImpl(SchoolsService service,ModelMapper modelMapper)
    {
        this.service = service;
        this.modelMapper=modelMapper;
    }

    @Override
    public void createExample(CreateExampleRequest request, StreamObserver<CreateExampleResponse> responseObserver) {

        CompletableFuture<Schools> Schoolsaved= service.save(Schools.builder()
                        .name(request.getName())
                        .location(request.getLocation())
                        .status(1).build());
        Schoolsaved.whenComplete((schools, throwable) -> {
            if (throwable != null) {
                responseObserver.onError(throwable);
            } else {
                //this line not works
                ExampleDto dto=modelMapper.map(schools,ExampleDto.class);
                dto=ExampleDto.newBuilder()
                        .setName(schools.getName())
                        .setLocation(schools.getLocation())
                        .setId(schools.getId().toString()).build();
                responseObserver.onNext(CreateExampleResponse.newBuilder().setExample(dto).build());
                responseObserver.onCompleted();
            }
        });

    }

    @Override
    public void getExamples(GetExamplesRequest request, StreamObserver<GetExamplesResponse> responseObserver) {

        CompletableFuture<List<Schools>> completeList=service.getAll();
        List<ExampleDto> schoolsGrpcList = new ArrayList<>();
        for(int i =0; i<completeList.join().size(); i++){
            ExampleDto schoolToadd = ExampleDto.newBuilder()
                    .setId(completeList.join().get(i).getId().toString())
                    .setLocation(completeList.join().get(i).getLocation())
                    .setName(completeList.join().get(i).getName())
                    .setStatus(completeList.join().get(i).getStatus())
                    .build();

            schoolsGrpcList.add(schoolToadd);
        }

        responseObserver.onNext(GetExamplesResponse.newBuilder().addAllExample(schoolsGrpcList).build());
        responseObserver.onCompleted();

    }

    @Override
    public void getExample(GetExampleRequest request, StreamObserver<GetExampleResponse> responseObserver) {
        CompletableFuture<Schools> schoolSerchead=service.getOneById(Long.valueOf(request.getId()));
        schoolSerchead.whenComplete((school, throwable) -> {
            if (throwable != null) {
                responseObserver.onError(throwable);
            } else {
                if(school==null){
                    responseObserver.onError(new Exception("not found"));
                }
                ExampleDto dto= ExampleDto.newBuilder()
                        .setId(school.getId().toString())
                        .setName(school.getName())
                        .setLocation(school.getLocation())
                        .setStatus(school.getStatus())
                        .build();
                responseObserver.onNext(GetExampleResponse.newBuilder().setExample(dto).build());
                responseObserver.onCompleted();
            }
        });

    }

    @Override
    public void updateExample(ExampleDto request, StreamObserver<UpdateExampleResponse> responseObserver) {
        Schools school=modelMapper.map(request,Schools.class);
        CompletableFuture<Schools> schoolsearched=service.getOneById(Long.valueOf(request.getId()))
                .thenCompose(schools -> service.save(school));

        schoolsearched.whenComplete((schoolrs, throwable) -> {
            if (throwable != null) {
                responseObserver.onError(throwable);
            } else {
                if(schoolrs==null){
                    responseObserver.onError(new Exception("not found"));
                }
                ExampleDto dto= ExampleDto.newBuilder()
                        .setId(schoolrs.getId().toString())
                        .setName(schoolrs.getName())
                        .setLocation(schoolrs.getLocation())
                        .setStatus(schoolrs.getStatus())
                        .build();
                responseObserver.onNext(UpdateExampleResponse.newBuilder().setExample(dto).build());
                responseObserver.onCompleted();
            }
        });

    }
}
