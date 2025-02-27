package online.beautyskin.beauty.mapper;

import online.beautyskin.beauty.entity.SkinQuestion;
import online.beautyskin.beauty.entity.request.SkinQuestionRequest;
import org.modelmapper.PropertyMap;

public class SkinQuestionMapper extends PropertyMap<SkinQuestionRequest, SkinQuestion> {
    @Override
    protected void configure() {
        map().setId(0);
    }
}
