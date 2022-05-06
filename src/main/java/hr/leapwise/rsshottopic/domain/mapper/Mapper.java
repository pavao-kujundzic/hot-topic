package hr.leapwise.rsshottopic.domain.mapper;


import org.modelmapper.ModelMapper;


public class Mapper {

    private static ModelMapper instance = new ModelMapper();

    public static <S, D> D map(S source, Class<D> type) {
        return instance.map(source, type);
    }

}
