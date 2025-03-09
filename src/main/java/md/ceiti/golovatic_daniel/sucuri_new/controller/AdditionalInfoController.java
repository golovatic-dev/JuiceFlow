package md.ceiti.golovatic_daniel.sucuri_new.controller;

import md.ceiti.golovatic_daniel.sucuri_new.model.DAO.additionalInfo.AdditionalInfoDAO;
import md.ceiti.golovatic_daniel.sucuri_new.model.DAO.additionalInfo.AdditionalInfoDAOImpl;
import md.ceiti.golovatic_daniel.sucuri_new.model.Product.entities.AdditionalProductInfo;

public class AdditionalInfoController {
    private AdditionalInfoDAO additionalInfoDAO;

    public AdditionalInfoController() {
        additionalInfoDAO = new AdditionalInfoDAOImpl();
    }

    public boolean insertAdditionalInfo(String additionalInfo, int id) {
        return additionalInfoDAO.insertAdditionalInfo(additionalInfo, id);
    }

    public boolean updateAdditionalInfo(int id, String additionalInfo) {
        return additionalInfoDAO.updateAdditionalInfo(id, additionalInfo);
    }

    public boolean deleteAdditionalInfo(int id) {
        return additionalInfoDAO.deleteAdditionalInfo(id);
    }

    public AdditionalProductInfo getAdditionalInfoById(int id) {
        return additionalInfoDAO.getAdditionalInfoById(id);
    }
}
