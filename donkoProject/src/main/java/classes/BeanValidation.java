package classes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class BeanValidation {
	
	public static <T> Map<String, String> validate (T bean){
		
		// Validator を取得
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        // バリデーションを実行
        Set<ConstraintViolation<T>> result = validator.validate(bean);
        
        //バリデーションメッセージを保持するmapをnew
        Map<String, String> validationMap = new LinkedHashMap<String, String>();
        
         //バリデーションメッセージのセット
        for(ConstraintViolation<T> rs : result){
        validationMap.put(rs.getPropertyPath().toString(), rs.getMessage());
        }
        
        return validationMap;

	}

}
