package md.ceiti.golovatic_daniel.sucuri_new.model.DAO.additionalInfo;

import md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities.AdditionalProductInfo;

public interface AdditionalInfoDAO {

    boolean insertAdditionalInfo(String additionalInfo, int productId);

    boolean updateAdditionalInfo(int id, String additionalInfo);

    boolean deleteAdditionalInfo(int id);

    AdditionalProductInfo getAdditionalInfoById(int id);
}
